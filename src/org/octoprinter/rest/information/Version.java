package org.octoprinter.rest.information;

import org.json.JSONObject;
import org.octoprinter.rest.OctoCommand;

public class Version extends OctoCommand {

    public Version() {
        super("/api/version", Method.GET);
    }



    @Override
    protected void dissectResult(JSONObject json) {
        if (json.has("api")) api = json.getString("api");
        if (json.has("server")) version = json.getString("server");
        if (json.has("text")) text = json.getString("text");
    }



    private String api = "0.0";
    private String version = "0.0.0";
    private String text = "OctoPrint";


    public String getApi() {
        return api;
    }

    public String getVersion() {
        return version;
    }

    public String getText() {
        return text;
    }
}
