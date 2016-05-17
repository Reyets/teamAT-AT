package serveur;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.*;

/**
 * Auteur : Axel Aiello
 * Class pour la commande ADD
 */
public class CommandeAdd implements Runnable {

    private JSONObject json;
    private PrintWriter out = null;

    public CommandeAdd(JSONObject json, PrintWriter out){
        this.json = json;
        this.out = out;
    }

    public void run() {
        JSONObject idee;
        try {
            idee = json.getJSONObject("idee");
            try {
                FileWriter fx = new FileWriter("D:/Projets/teamAT-AT/AxelThibaut/serverAT/ressources/idea.txt", true);
                fx.write(idee.toString());
                fx.write("\n");
                fx.close();
                printerOk();
            } catch (IOException e) {
                printerBadRequest();
            }

        } catch (JSONException e) {
            printerBadRequest();
        }
    }

    private void printerBadRequest() {
        out.println("{\"status\":\"BAD REQUEST\"}");
        out.flush();
    }

    private void printerOk() {
        out.println("{\"status\":\"OK\"}");
        out.flush();
    }

}
