package org.octoprinter.rest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;


public class OctoStruct {

    protected Logger logger = LogManager.getLogger("octostruct");

    protected OctoStruct(JSONObject json) {
        this.json = json;
    }



    private JSONObject json;



    protected boolean getBoolean(String key) {
        return getBoolean(key, false);
    }
    protected boolean getBoolean(String key, boolean __default) {
        try {
            if (json.has(key)) return json.getBoolean(key);
        } catch (JSONException ex) {
            return __default;
        }
        return __default;
    }

    protected float getFloat(String key) {
        return getFloat(key, 0.0f);
    }
    protected float getFloat(String key, float __default) {
        try {
            if (json.has(key)) return json.getFloat(key);
        } catch (JSONException ex) {
            return __default;
        }
        return __default;
    }

    protected String getString(String key) {
        return getString(key, "n/a");
    }
    protected String getString(String key, String __default) {
        try {
            if (json.has(key)) return json.getString(key);
        } catch (JSONException ex) {
            return __default;
        }
        return __default;
    }

    protected int getInteger(String key) {
        return getInteger(key, 0);
    }
    protected int getInteger(String key, int __default) {
        try {
            if (json.has(key)) return json.getInt(key);
        } catch (JSONException ex) {
            return __default;
        }
        return __default;
    }

}
