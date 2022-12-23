package org.octoprinter.rest.structs;

import org.json.JSONObject;
import org.octoprinter.rest.OctoStruct;

public class SD extends OctoStruct {

    public SD() {
        super(new JSONObject());
    }

    public SD(JSONObject json) {
        super(json);
        ready = getBoolean("ready");
    }

    private boolean ready;
    public boolean isReady() { return ready; }

}
