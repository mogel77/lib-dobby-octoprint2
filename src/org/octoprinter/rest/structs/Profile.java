package org.octoprinter.rest.structs;

import org.json.JSONObject;
import org.octoprinter.rest.OctoStruct;

public class Profile extends OctoStruct {

    public Profile() {
        super(new JSONObject());
    }

    public Profile(JSONObject json) {
        super(json);

        id = getString("id");
        name = getString("name");
        color = getString("color");
        model = getString("model");
        __default = getBoolean("default");
        current = getBoolean("current");
        resource = getString("resource");
        volume = new Volume(json.getJSONObject("volume"));
        heatedBed = getBoolean("heatedBed");
        heatedChamber = getBoolean("heatedChamber");
        JSONObject axes = json.getJSONObject("axes");
        x = new Axis(axes.getJSONObject("x"));
        y = new Axis(axes.getJSONObject("y"));
        z = new Axis(axes.getJSONObject("z"));
    }

    private String id = "profile";
    private String name = "profile";
    private String color = "default";
    private String model = "Generic RepRap Printer";
    private boolean __default = false;
    private boolean current = false;
    private String resource = "";

    private Volume volume = new Volume();

    private boolean heatedBed = false;
    private boolean heatedChamber = false;

    private Axis x = new Axis();
    private Axis y = new Axis();
    private Axis z = new Axis();


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public String getModel() {
        return model;
    }

    public boolean isDefault() {
        return __default;
    }

    public boolean isCurrent() {
        return current;
    }

    public String getResource() {
        return resource;
    }

    public Volume getVolume() {
        return volume;
    }

    public boolean isHeatedBed() {
        return heatedBed;
    }

    public boolean isHeatedChamber() {
        return heatedChamber;
    }

    public Axis getX() {
        return x;
    }

    public Axis getY() {
        return y;
    }

    public Axis getZ() {
        return z;
    }
}
