package org.octoprinter.rest.command;

import org.json.JSONObject;
import org.octoprinter.rest.OctoCommand;
import org.octoprinter.rest.structs.File;

public class FilesOperator extends OctoCommand {

    public FilesOperator() {
        super("/api/files", Method.POST);
    }

    @Override
    protected void dissectResult(JSONObject json) { /* nüschts */}

    public void select4Print(File file) {
        String origin = file.getOrigin();
        String name = file.getName();

        changePath("/api/files/" + origin + "/" + name);

        getParams().put("command", "select");
        getParams().put("print", false);
    }

}
