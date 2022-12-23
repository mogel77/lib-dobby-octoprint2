package org.octoprinter.rest.command;

import org.json.JSONObject;
import org.octoprinter.rest.OctoCommand;


public class Hotend extends OctoCommand {

    public Hotend() {
        super("/api/printer/tool", Method.POST);
    }

    @Override
    protected void dissectResult(JSONObject json) { /* nix */ }

    /**
     * legt die temperatur für die einzelnen Hotends fest
     * @param tool eine Sammlung aller Hotends
     * @param temperature deren zugehörige Temperaturen
     */
    public void setTargetTemperatures(String[] tool, int[] temperature) {
        if (tool.length != temperature.length) return;
        JSONObject json = getParams();
        json.put("command", "target");
        JSONObject targets = new JSONObject();
        for(int i = 0; i < tool.length; i++) targets.put(tool[i], temperature[i]);
        json.put("targets", targets);
    }

    /**
     * legt die Temperatur für "tool0" fest
     * @param temperature
     */
    public void setTargetTemperaturTool0(int temperature) {
        setTargetTemperatures(new String[] { "tool0" }, new int[] { temperature });
    }

    public void setExtrude(int amount) {
        JSONObject json = getParams();
        json.put("command", "extrude");
        json.put("amount", amount); // negativ ist Retract
    }

    public void setFlowrate(int amount) {
        JSONObject json = getParams();
        json.put("command", "extrude");
        json.put("amount", amount);
    }

    public void selectTool(String tool) {
        JSONObject json = getParams();
        json.put("command", "select");
        json.put("tool", tool);
    }

}
