package org.octoprinter.rest.structs;

import org.json.JSONObject;
import org.octoprinter.rest.OctoStruct;

public class Filament extends OctoStruct {

    public Filament(String name) {
        super(new JSONObject());
        this.name = name;
    }
    public Filament(String name, JSONObject json) {
        super(json);
        this.name = name;
        length = getFloat("length");
        volume = getFloat("volume");
    }

    private String name;
    private float length;
    private float volume;

    public String getName() {
        return name;
    }

    public float getLength() {
        return length;
    }

    public float getVolume() {
        return volume;
    }
}
