package org.octoprinter.rest.information;

import org.json.JSONObject;
import org.octoprinter.rest.OctoCommand;
import org.octoprinter.rest.structs.Webcam;



public class Settings extends OctoCommand {

    public Settings() {
        super("/api/settings", Method.GET);
    }

    private Webcam webcam = new Webcam();

    @Override
    protected void dissectResult(JSONObject json) {
        if (json.has("webcam")) webcam = new Webcam(json.getJSONObject("webcam"));
    }


    public Webcam getWebcam() {
        return webcam;
    }
}
