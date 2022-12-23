package org.octoprinter.rest.command;

import org.json.JSONObject;
import org.octoprinter.rest.OctoCommand;
import org.octoprinter.rest.OctoPrinter;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class GCode extends OctoCommand {

    public static void sendSingleGCode(OctoPrinter printer, String gcode) {
        GCode g = new GCode();
        g.addGCode(gcode);
        g.prepare2Send();
        printer.execute(g);
    }

    public GCode() {
        super("/api/printer/command", Method.POST);
    }

    private Queue<String> gcodes = new ArrayDeque<>();

    @Override
    protected void dissectResult(JSONObject json) { /* nix */ }

    public void addGCode(String gcode) {
        gcodes.add(gcode);
    }

    public void prepare2Send() {
        JSONObject json = getParams();
        json.put("commands", gcodes.toArray());
    }

}
