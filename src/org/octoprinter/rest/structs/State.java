package org.octoprinter.rest.structs;

import org.json.JSONObject;
import org.octoprinter.rest.OctoStruct;

public class State extends OctoStruct {

    public State() {
        super(new JSONObject());
    }

    public State(JSONObject json) {
        super(json);

        text = getString("text");

        operational = getBoolean("operational");
        paused = getBoolean("paused");
        printing = getBoolean("printing");
        cancelling = getBoolean("cancelling");
        pausing = getBoolean("pausing");
        sdReady = getBoolean("sdReady");
        error = getBoolean("error");
        ready = getBoolean("ready");
        closedOrError = getBoolean("closedOrError");
    }



    private String text;
    private boolean operational;
    private boolean paused;
    private boolean printing;
    private boolean cancelling;
    private boolean pausing;
    private boolean sdReady;
    private boolean error;
    private boolean ready;
    private boolean closedOrError;


    public String getText() {
        return text;
    }

    public boolean isOperational() {
        return operational;
    }

    public boolean isPaused() {
        return paused;
    }

    public boolean isPrinting() {
        return printing;
    }

    public boolean isCancelling() {
        return cancelling;
    }

    public boolean isPausing() {
        return pausing;
    }

    public boolean isSdReady() {
        return sdReady;
    }

    public boolean isError() {
        return error;
    }

    public boolean isReady() {
        return ready;
    }

    public boolean isClosedOrError() {
        return closedOrError;
    }
}
