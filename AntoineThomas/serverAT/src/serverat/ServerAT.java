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

public class ServerAT {
    private final BaseDeDonnee bdd;
    private final JSONParser parser;

    public static void main(String[] zero) {
        ServerAT at = new ServerAT();
        at.init();
    }

    public ServerAT() {
        bdd = new BaseDeDonnee();
        parser = new JSONParser();
    }
    
    public void init() {
        ServerSocket serv;
        Socket socks;
        BufferedReader in;
        BufferedWriter out;
        try {
            serv = new ServerSocket(9999);
            socks = serv.accept();
            System.out.println(socks.getInetAddress() + " Connecté");
           
            in = new BufferedReader(new InputStreamReader(socks.getInputStream()));
            String recu = in.readLine();            
            JSONObject reponse = managecall(recu);
            System.out.println("reponse : " + reponse.toJSONString());
            System.out.println("recu : "  + recu);
            
            out = new BufferedWriter(new OutputStreamWriter(socks.getOutputStream()));
            out.write(reponse.toJSONString());
            out.flush();
            
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
            Logger.getLogger(ServerAT.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bdd.JSONStatus(new JSONObject(), true);
    }

}
