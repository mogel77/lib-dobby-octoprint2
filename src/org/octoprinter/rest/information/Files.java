package org.octoprinter.rest.information;

import org.json.JSONArray;
import org.json.JSONObject;
import org.octoprinter.rest.OctoCommand;
import org.octoprinter.rest.structs.File;

import java.util.ArrayList;
import java.util.List;

public class Files extends OctoCommand {

    public Files() {
        super("/api/files", Method.GET);
    }

    private float free = 0.0f;
    private List<File> files = new ArrayList<>();   // TODO sync!

    public float getFree() { return free; }
    public List<File> getFiles() { return files; }
    @Override
    protected void dissectResult(JSONObject json) {
        if (json.has("free")) free = json.getFloat("free");
        if (json.has("files")) dissectFiles(json.getJSONArray("files"));
    }

    private void dissectFiles(JSONArray files) {
        this.files.clear();
        for(Object o : files) {
            File file = new File((JSONObject) o);
            this.files.add(file);
        }
        logger.info(files.length() + " Dateien gefunden");
    }

}
