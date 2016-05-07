package serveur;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class Authentification implements Runnable {

    private Socket socket;
    private PrintWriter out = null;
    private BufferedReader in = null;
    private String order = "";

    public Authentification(Socket s){
        socket = s;
    }

    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());

            while(true){
                out.println("Entrez votre demande :");
                out.flush();
                order = in.readLine();

                isValid(order, out);
            }

        } catch (IOException e) {
            System.err.println("ERROR");
        }
    }

    private static void isValid(String order, PrintWriter out) {
        JSONObject json = new JSONObject(order);
        String request;

        // On cherche quel type de requête
        try {
            request = json.getString("request");

            // On rajout une idée
            if (request.equals("ADD")) {
                JSONObject idee;
                try {
                    idee = json.getJSONObject("idee");
                    try {
                        FileWriter fx = new FileWriter("D:/Projets/teamAT-AT/AxelThibaut/serverAT/ressources/idea.txt", true);
                        fx.write(idee.toString());
                        fx.write("\n");
                        fx.close();
                    } catch (IOException e) {
                        out.println("{\"status\":\"BAD REQUEST\"}");
                        out.flush();
                        return;
                    }

                } catch (JSONException e) {
                    out.println("{\"status\":\"BAD REQUEST\"}");
                    out.flush();
                    return;
                }
                out.println("{\"status\":\"OK\"}");
                out.flush();
                return;
            }

            // On participe
            else if (request.equals("PARTICIPE")) {

            }

            // On veut une liste
            else if (request.equals("LIST")) {
                String idee;

                // Des participant d'un idée
                try {
                    idee = json.getString("idee");
                }

                // Des idées
                catch (JSONException e) {
                    //{"idee":["server java","server java2","server java3"],"status":"OK"}
                    try {
                        String message = "{\"list\":[";
                        Scanner sc = new Scanner(new File("D:/Projets/teamAT-AT/AxelThibaut/serverAT/ressources/idea.txt"));
                        while(sc.hasNext()){
                            message = message + "{\"idee\":" + sc.nextLine() + "},";
                        }
                        if (!message.equals("{\"list\":[")) {
                            message = message.substring(0, message.length() - 1);
                        }
                        message = message + "],\"status\":\"OK\"}";
                        out.println(message);
                        out.flush();
                        return;
                } catch (FileNotFoundException f) {
                        out.println("{\"status\":\"BAD REQUEST\"}");
                        out.flush();
                        return;
                    }
                }

            }

            // ERROR
            else {
                out.println("{\"status\":\"BAD REQUEST\"}");
                out.flush();
                return;
            }
        }
        // Pas compris
        catch (JSONException e) {
            out.println("{\"status\":\"BAD REQUEST\"}");
            out.flush();
            return;
        }
        out.println("{\"status\":\"BAD REQUEST\"}");
        out.flush();
        return;
    }


}
