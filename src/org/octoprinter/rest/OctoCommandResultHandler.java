package org.octoprinter.rest;

import org.json.JSONObject;

public interface OctoCommandResultHandler {

    /** verarbeitet das Ergebnis des Befehls */
    void handleCommandResult(OctoCommand sender);

    /** verarbeitet die mögliche Exception die bei dem Befehl aufgetreten ist */
    void handleCommandException(OctoCommand sender, Exception ex);

}
