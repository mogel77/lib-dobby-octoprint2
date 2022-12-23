package org.octoprinter.rest.command;

import org.json.JSONObject;
import org.octoprinter.rest.OctoCommand;

public class Head extends OctoCommand {

    public Head() {
        super("/api/printer/printhead", OctoCommand.Method.POST);
    }

    @Override
    protected void dissectResult(JSONObject json) { /* nix */ }

    public void setFeedRate(int feedrate) {
        JSONObject json = getParams();
        json.put("command", "feedrate");
        json.put("factor", feedrate);
    }

    public void jogHomeXY() {
        jogHome(new String[] { "x", "y" });
    }
    public void jogHomeZ() {
        jogHome(new String[] { "z" });
    }
    public void jogHome(String[] axis) {
        JSONObject json = getParams();
        json.put("command", "home");
        json.put("axes", axis);
    }

    public void jogAxisXYZ(String[] axis, int[] amount) {
        JSONObject json = getParams();
        json.put("command", "jog");
        json.put("absolute", false);
        for(int i = 0; i < axis.length; i++) json.put(axis[i], amount[i]);
    }
    public void jogAxisX(int amount) {
        jogAxisXYZ(new String[] { "x" }, new int[] { amount });
    }
    public void jogAxisY(int amount) {
        jogAxisXYZ(new String[] { "y" }, new int[] { amount });
    }
    public void jogAxisZ(int amount) {
        jogAxisXYZ(new String[] { "z" }, new int[] { amount });
    }

    public void jogAxisToXYZ(String[] axis, int[] amount) {
        JSONObject json = getParams();
        json.put("command", "jog");
        json.put("absolute", true);
        for(int i = 0; i < axis.length; i++) json.put(axis[i], amount[i]);
    }
    public void jogAxisToX(int amount) {
        jogAxisToXYZ(new String[] { "x" }, new int[] { amount });
    }
    public void jogAxisToY(int amount) {
        jogAxisToXYZ(new String[] { "y" }, new int[] { amount });
    }
    public void jogAxisToZ(int amount) {
        jogAxisToXYZ(new String[] { "z" }, new int[] { amount });
    }

}
