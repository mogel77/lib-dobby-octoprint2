package org.octoprinter.rest.structs;

import org.json.JSONObject;
import org.octoprinter.rest.OctoStruct;

public class Axis extends OctoStruct {

    protected Axis() {
        super(new JSONObject());
    }

    protected Axis(JSONObject json) {
        super(json);

        speed = getInteger("speed");
        inverted = getBoolean("inverted");
    }

    private int speed;
    private boolean inverted;


    public boolean isInverted() {
        return inverted;
    }

    public int getSpeed() {
        return speed;
    }

}
