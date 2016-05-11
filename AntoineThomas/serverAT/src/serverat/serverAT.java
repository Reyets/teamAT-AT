package serverat;

import data.BaseDeDonnee;
import data.Participant;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class serverAT {

    private final BaseDeDonnee bdd = new BaseDeDonnee();
    private final JSONParser parser = new JSONParser();

    public  void main(String[] zero) {
        ServerSocket serv;
        Socket socks;
        BufferedReader in;
        PrintWriter out;
        try {
            serv = new ServerSocket(9999);
            socks = serv.accept();

            out = new PrintWriter(socks.getOutputStream());
            out.println("Connecté");
            out.flush();

            in = new BufferedReader(new InputStreamReader(socks.getInputStream()));
            String recu = in.readLine();
            JSONObject reponse = managecall(recu);
            out.println(reponse.toJSONString());
            System.out.println("reponse : " + reponse);
            System.out.println("recu : "  + recu);
            socks.close();
            serv.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private JSONObject managecall(String in) {
        try {
            JSONObject get = (JSONObject) parser.parse(in);
            if (!get.containsKey("action")) {
                return bdd.JSONStatus(new JSONObject(), false);
            } else {
                String action = (String) get.get("action");
                if (action.equalsIgnoreCase("list")) {
                    return bdd.JSONStatus(bdd.getJSONList(), true);
                } else if (action.equalsIgnoreCase("participe")) {
                 //   JSONObject p = (JSONObject) get.get("participant");
                  //  bdd.JSONStatus(bdd.addInteresse(p, 0), true);
                } else if (action.equalsIgnoreCase("add")) {
                   // JSONObject p = (JSONObject) get.get("idee");
                    // bdd.JSONStatus(bdd.addIdee(), true);
                } else {
                    return bdd.JSONStatus(new JSONObject(), false);
                }
            }

        } catch (ParseException ex) {
            Logger.getLogger(serverAT.class.getName()).log(Level.SEVERE, null, ex);
        }

        return bdd.JSONStatus(new JSONObject(), true);
    }

}
