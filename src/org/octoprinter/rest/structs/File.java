package org.octoprinter.rest.structs;

import org.json.JSONObject;
import org.octoprinter.rest.OctoStruct;

import java.util.Map;

public class File extends OctoStruct {

    public File() {
        super(new JSONObject());
    }
    public File(JSONObject json) {
        super(json);
        name = getString("name");
        origin = getString("origin");
        size = getInteger("size");  // Zeilen?
        date = getInteger("date");
    }

    private String name = "init";
    private String origin = "local";
    private int size;
    private int date;


    public String getName() {
        return name;
    }

    public String getOrigin() {
        return origin;
    }

    public int getSize() {
        return size;
    }

    public int getDate() {
        return date;
    }
}
