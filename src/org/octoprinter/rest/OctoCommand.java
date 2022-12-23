package org.octoprinter.rest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class OctoCommand {

    protected Logger logger = LogManager.getLogger("octocommand");

    public enum Method {
        GET,
        POST
    }

    protected OctoCommand(String newPath, Method newMethod) {
        path = newPath;
        method = newMethod;
        params = new JSONObject();
    }

    /** ändert den Pfad in der URL - nötig ?! */
    protected void changePath(String newPath) { path = newPath; }

    protected abstract void dissectResult(JSONObject json);

    private String path;
    private Method method;
    private JSONObject params;



    public String getPath() { return path; }
    public Method getMethod() { return method; }
    public JSONObject getParams() { return params; }
    public boolean hasParams() { return params.length() != 0; }



    private List<OctoCommandResultHandler> listener = Collections.synchronizedList(new ArrayList<>());

    /**
     * @param listener
     */
    public synchronized void addListener(OctoCommandResultHandler listener) {
        this.listener.add(listener);
    }

    /**
     * @param listener
     */
    public synchronized  void removeListener(OctoCommandResultHandler listener) {
        this.listener.remove(listener);
    }

    /** */
    public synchronized void invokeListener() {
        for (OctoCommandResultHandler l: listener ) l.handleCommandResult(this);
    }

    /**
     *
     * @param ex
     */
    public void invokeListener(Exception ex) {
        for (OctoCommandResultHandler l: listener ) l.handleCommandException(this, ex);
    }

}
