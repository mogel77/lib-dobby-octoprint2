package org.octoprinter.rest.structs;

import org.json.JSONObject;
import org.octoprinter.rest.OctoStruct;

public class Temperature extends OctoStruct {

    /**
     * @param name der Name ist der Key im übergeordnetem Json
     */
    public Temperature(String name) {
        super(new JSONObject());
        this.name = name;
    }

    /**
     * @param name der Name ist der Key im übergeordnetem Json
     * @param json
     */
    public Temperature(String name, JSONObject json) {
        super(json);
        actual = getFloat("actual");
        target = getFloat("target");
        offset = getFloat("offset");
        this.name = name;
    }


    private float actual;
    private float target;
    private float offset;
    private String name;



    public float getActual() { return actual; }

    public float getTarget() { return target; }

    public float getOffset() { return offset; }

    public String getName() { return name; }

}
