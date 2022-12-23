package org.octoprinter.rest.information;

import org.json.JSONObject;
import org.octoprinter.rest.OctoCommand;
import org.octoprinter.rest.structs.Profile;

import java.util.HashMap;
import java.util.Map;

public class PrinterProfiles extends OctoCommand {

    public PrinterProfiles() {
        super("/api/printerprofiles", Method.GET);
    }



    private Map<String, Profile> profiles = new HashMap<>();



    @Override
    protected void dissectResult(JSONObject json) {
        logger.info("lade Profile vom Drucker");
        JSONObject p = json.getJSONObject("profiles");
        for(String name : p.keySet()) {
            logger.info(" - " + name);
            profiles.put(name, new Profile(p.getJSONObject(name)));
        }
    }

    public Map<String, Profile> getProfiles() {
        return profiles;
    }

    public Profile getProfile(String name) {
        if (profiles.containsKey(name)) return profiles.get(name);
        return new Profile();
    }

    public Profile getCurrent() {
        for(Profile p : profiles.values()) {
            if (p.isCurrent()) return p;
        }
        return new Profile();
    }

}
