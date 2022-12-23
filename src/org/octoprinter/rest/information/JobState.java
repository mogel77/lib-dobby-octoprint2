package org.octoprinter.rest.information;

import org.json.JSONObject;
import org.octoprinter.rest.OctoCommand;
import org.octoprinter.rest.structs.Filament;
import org.octoprinter.rest.structs.File;
import org.octoprinter.rest.structs.Progress;

import java.util.HashMap;
import java.util.Map;


public class JobState extends OctoCommand {

    public JobState() {
        super("/api/job", Method.GET);
    }

    private String state = "initialisiere";
    private Progress progress = new Progress();
    private File file = new File();
    private int estimatedPrintTime;
    private int lastPrintTime;
    private int averagePrintTime;
    private Map<String, Filament> filaments = new HashMap<String, Filament>();

    @Override
    protected void dissectResult(JSONObject json) {
        if (json.has("state")) state = json.getString("state");
        if (json.has("job")) dissectJob(json.getJSONObject("job"));
        if (json.has("progress")) progress = new Progress(json.getJSONObject("progress"));
    }

    private void dissectJob(JSONObject json) {
        if (json.has("file")) file = new File(json.getJSONObject("file"));
        if (json.has("filament")) dissectFilaments(json.getJSONObject("filament"));
        if (json.has("estimatedPrintTime")) estimatedPrintTime = json.getInt("estimatedPrintTime");
    }
    private void dissectFilaments(JSONObject json) {
        for(String name : json.keySet()) {
            filaments.put(name, new Filament(name, json.getJSONObject(name)));
        }
    }


    public String getState() {
        return state;
    }

    public Progress getProgress() {
        return progress;
    }

    public File getFile() {
        return file;
    }

    public Map<String, Filament> getFilaments() {
        return filaments;
    }

    public Filament getFilaments(String name) {
        if (filaments.containsKey(name)) return filaments.get(name);
        return new Filament("tool0");
    }

    public int getEstimatedPrintTime() {
        return estimatedPrintTime;
    }

    public int getLastPrintTime() {
        return lastPrintTime;
    }

    public int getAveragePrintTime() {
        return averagePrintTime;
    }
}
