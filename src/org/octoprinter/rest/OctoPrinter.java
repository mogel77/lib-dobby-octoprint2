package org.octoprinter.rest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.octoprinter.rest.command.Hotend;
import org.octoprinter.rest.information.PrinterProfiles;
import org.octoprinter.rest.information.Settings;
import org.octoprinter.rest.information.Version;
import org.octoprinter.rest.structs.Profile;


public class OctoPrinter {

    protected Logger logger = LogManager.getLogger("printer");

    /**
     * Verbindet zum lokalen Octoprinter
     * @param newapiKey
     */
    public OctoPrinter(String newapiKey) {
        this("http://127.0.0.1", newapiKey);
    }

    /**
     * Verbindet zu einem speziellem Octoprinter
     * @param host
     * @param apiKey
     */
    public OctoPrinter(String host, String apiKey) {
        this.host = host;
        this.apiKey = apiKey;
        logger.info("neuen Printer angelegt - " + host);
    }

    private Version version = new Version();
    private PrinterProfiles profiles = new PrinterProfiles();
    private Settings settings = new Settings();
    private String host;
    private String apiKey;



    public Version getVersion() { return version; }
    public PrinterProfiles getProfiles() { return profiles; }
    public Settings getSettings() { return settings; }



    /**
     * führt einen Befehl an den Printer SYNC aus
     * @param command
     */
    public void execute(OctoCommand command) {
        new Thread( () -> executeSync(command) ).start();
    }

    /**
     * führt einen Befehl an den Print SYNC aus
     * @param command
     */
    public void executeSync(OctoCommand command) {
        try {
            String url = "http://" + host + command.getPath();
            HttpURLConnection connection = createConnection(command.getMethod().toString(), url, apiKey);
            if (command.hasParams()) addParams(connection, command);
            final String jsonString = handleConnection(connection);
            setConnection(true);
            if (204 == connection.getResponseCode()) return; // no Content
            JSONObject json = new JSONObject(jsonString);
            command.dissectResult(json);
            command.invokeListener();
        } catch(IOException ex) {
            // Probleme mit der Verbindung
            logger.error(ex.getMessage());
            setConnection(false);
            command.invokeListener(ex);
        } catch(JSONException ex) {
            // was anderes aufgetreten
            logger.error("JSON: " + ex.getMessage());
        }
    }

    private HttpURLConnection createConnection(String method, String url, String apiKey) throws IOException {
        URL apiUrl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection)apiUrl.openConnection();
        connection.setRequestProperty("X-Api-Key", apiKey);
        connection.setRequestProperty("Content-Type","application/json");
        connection.setRequestMethod(method);
        connection.setDoInput(true);
        connection.setDoOutput(true);
        return connection;
    }

    private String handleConnection(final HttpURLConnection connection) throws IOException {
        String output = this.getOutput(connection);
        return output;
    }

    private String getOutput(HttpURLConnection connection) throws IOException {
        String result = "";
        try(final BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String temp = null;
            while((temp = br.readLine()) != null) result = result + temp;
            connection.disconnect();
        } catch(final IOException e) {
            throw e;
        }
        return result;
    }

    private void addParams(HttpURLConnection connection, OctoCommand command) {
        try {
            OutputStream os = connection.getOutputStream();
            os.write(command.getParams().toString().getBytes());
            os.flush();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }


    //region . Verbindungsstatus und -verwaltung .
    private List<OctoPrinterConnectionListener> listener = Collections.synchronizedList(new ArrayList<>());
    private boolean connected;
    /** liefert den aktuellen Status der Verbindung */
    public boolean isConnected() {
        return connected;
    }
    /** ändert den Verbindungsstatus und informiert die Listener
     * @param status */
    private void setConnection(boolean status) {
        synchronized (listener) {
            if (connected == status) return;
            logger.info("CONNECTION: " + status);
            connected = status;
            if (status) {
                executeSync(version);
                executeSync(profiles);
                executeSync(settings);
            }
            for (OctoPrinterConnectionListener l : listener ) {
                if (status) {
                    logger.info("verbunden mit " + version.getText());
                    l.onConnected();
                } else {
                    l.onDisconnected();
                }
            }
        }
    }
    public void addConnectionListener(OctoPrinterConnectionListener l) {
        synchronized(listener) {
            listener.add(l);
        }
    }
    public void removeConnectionListener(OctoPrinterConnectionListener l) {
        synchronized(listener) {
            listener.remove(l);
        }
    }
    //endregion

}
