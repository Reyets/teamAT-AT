package serveur;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.*;
import java.util.Scanner;

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
            int i = 0;
            String message = "";
            idee = json.getJSONObject("idee");
            System.out.println(1);
            try {
                FileWriter fx = new FileWriter("D:/Projets/teamAT-AT/AxelThibaut/serverAT/ressources/idea.txt", true);
                try {
                    Scanner sc = new Scanner(new File("D:/Projets/teamAT-AT/AxelThibaut/serverAT/ressources/participant.txt"));
                    while (sc.hasNext()) {
                        String msg = sc.nextLine();
                        JSONObject participant = new JSONObject(msg);
                        if (participant.getJSONObject("Participant").getString("nom").equals(idee.getString("nom")) && participant.getJSONObject("Participant").getString("email").equals(idee.getString("mail"))) {
                            message = msg;
                        }
                        i++;
                    }
                    if (message.equals("")) {
                        message = "{\"Participant\":{\"identifiant\":" + i + ",\"nom\":\"" + idee.getString("nom") + "\",\"email\":\"" + idee.getString("mail") + "\"}}";
                        FileWriter fx2 = new FileWriter("D:/Projets/teamAT-AT/AxelThibaut/serverAT/ressources/participant.txt", true);
                        fx2.write(message);
                        fx2.write("\n");
                        fx2.close();
                    }
                } catch (FileNotFoundException f) {
                    printerBadRequest();
                }
                String msg = idee.toString().substring(1,idee.toString().length() - 1);
                msg = "{" + msg + ",\"interesses\":[\"" +  message + "\"]}";
                //{"Participant":{"identifiant":0,"nom":"Thomas Jalabert","email":"thom.jalabert@gmail"}}
                fx.write(msg);
                fx.write("\n");
                fx.close();
                printerOk();
            } catch (IOException e) {
                System.out.println(3);
                printerBadRequest();
            }

        } catch (JSONException e) {
            System.out.println(2);
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
