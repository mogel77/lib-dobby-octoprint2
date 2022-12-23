package org.octoprinter.rest.structs;

import org.json.JSONObject;
import org.octoprinter.rest.OctoStruct;

public class Webcam extends OctoStruct {

    public Webcam() {
        super(new JSONObject());
    }

    public Webcam(JSONObject json) {
        super(json);
        streamUrl = getString("streamUrl");
        snapshotUrl = getString("snapshotUrl");
        ffmpegPath = getString("ffmpegPath");
    }



    private String streamUrl = "";
    private String snapshotUrl = "";
    private String ffmpegPath = "";



    public String getStreamUrl() {
        return streamUrl;
    }

    public String getSnapshotUrl() {
        return snapshotUrl;
    }

    public String getFfmpegPath() {
        return ffmpegPath;
    }

}
