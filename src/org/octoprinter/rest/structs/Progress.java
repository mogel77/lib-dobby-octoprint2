package org.octoprinter.rest.structs;

import org.json.JSONObject;
import org.octoprinter.rest.OctoStruct;

public class Progress extends OctoStruct {

    public Progress() {
        super(new JSONObject());
    }

    public Progress(JSONObject json) {
        super(json);

        completion = getFloat("completion");
        filepos = getInteger("filepos");
        printTime = getInteger("printTime");
        printTimeLeft = getInteger("printTimeLeft");
    }

    private float completion;
    private int filepos;
    private int printTime;
    private int printTimeLeft;

    public float getCompletion() {
        return completion;
    }

    public int getFilepos() {
        return filepos;
    }

    public int getPrintTime() {
        return printTime;
    }

    public int getPrintTimeLeft() {
        return printTimeLeft;
    }
}
