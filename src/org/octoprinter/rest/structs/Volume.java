package org.octoprinter.rest.structs;

import org.json.JSONObject;
import org.octoprinter.rest.OctoStruct;

public class Volume extends OctoStruct {

    protected Volume() {
        super(new JSONObject());
    }

    protected Volume(JSONObject json) {
        super(json);

        formFactor = getString("formFactor");
        origin = getString("origin");
        width = getInteger("width");
        height = getInteger("height");
        depth = getInteger("depth");
    }



    private String formFactor = "rectangular";
    private String origin = "lowerleft";
    private int width = 100;
    private int depth = 100;
    private int height = 100;


    public String getFormFactor() {
        return formFactor;
    }

    public String getOrigin() {
        return origin;
    }

    public int getWidth() {
        return width;
    }

    public int getDepth() {
        return depth;
    }

    public int getHeight() {
        return height;
    }
}
