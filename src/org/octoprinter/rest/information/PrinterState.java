package org.octoprinter.rest.information;

import org.apache.logging.log4j.LogManager;
import org.json.JSONObject;
import org.octoprinter.rest.OctoCommand;
import org.octoprinter.rest.structs.SD;
import org.octoprinter.rest.structs.State;
import org.octoprinter.rest.structs.Temperature;

import java.util.HashMap;
import java.util.Map;



public class PrinterState extends OctoCommand {

    public PrinterState() {
        super("/api/printer", Method.GET);
        logger = LogManager.getLogger("PrinterState");
    }



    public State getState() { return state; }
    public SD getSD() { return sd; }
    public String getText() { return text; }
    public Map<String, Temperature> getTemperatures() { return temperatures; }
    public Temperature getTemperature(String name) {
        if (temperatures.containsKey(name)) return temperatures.get(name);
        return new Temperature(name);
    }

    private SD sd = new SD();
    private Map<String, Temperature> temperatures = new HashMap<String, Temperature>();
    private State state = new State();
    private String text = "nüschts los hier";



    @Override
    protected void dissectResult(JSONObject json) {
        // for (String keys : json.keySet()) logger.info("KEYS: " + keys);
        if (json.has("sd")) sd = new SD(json);
        if (json.has("temperature")) dissectTemperature(json.getJSONObject("temperature"));
        if (json.has("state")) dissectState(json.getJSONObject("state"));
    }

    private void dissectTemperature(JSONObject json) {
        for (String name : json.keySet()) {
            if (!name.equals("history")) temperatures.put(name, new Temperature(name, json.getJSONObject(name)));
        }
    }

    private void dissectState(JSONObject json) {
        if (json.has("text")) text = json.getString("text");
        if (json.has("flags")) state = new State(json.getJSONObject("flags"));
    }

}
