package org.octoprinter.rest.command;

import org.json.JSONObject;
import org.octoprinter.rest.OctoCommand;

public class JobControl extends OctoCommand {

    public JobControl() {
        super("/api/job", Method.GET);
    }

    @Override
    protected void dissectResult(JSONObject json) { /* nix */ }

    public void startPrint() {
        JSONObject json = getParams();
        json.put("command", "start");
    }

    public void cancelPrint() {
        JSONObject json = getParams();
        json.put("command", "cancel");
    }

    public void restartPrint() {
        JSONObject json = getParams();
        json.put("command", "restart");
    }

    public void pausePrint() {
        JSONObject json = getParams();
        json.put("command", "pause");
        json.put("action", "toggle");
    }

}
