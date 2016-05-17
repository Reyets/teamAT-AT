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
        PrintWriter out;

        try {
            serv = new ServerSocket(9999);
            System.out.println("Server LAUNCH FAGGOT !");
            while (true) {
                socks = serv.accept();
                in = new BufferedReader(new InputStreamReader(socks.getInputStream()));
                System.out.println(socks.getInetAddress() + " Connecté");
                String recu = in.readLine();

                if (recu.equalsIgnoreCase("quit")) {
                    break;
                }
                JSONObject reponse = managecall(recu);
                System.out.println("recu : " + recu);
                System.out.println("reponse : " + reponse.toJSONString());

                out = new PrintWriter(socks.getOutputStream());
                out.println(reponse.toJSONString());
                out.flush();
                socks.close();
            }
            serv.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private JSONObject managecall(String in) {
        try {

            JSONObject jsonObject = (JSONObject) parser.parse(in);
            if (!jsonObject.containsKey("request")) {
                return bdd.JSONStatus(new JSONObject(), false);
            } else {
                String action = (String) jsonObject.get("request");
                if (action.equalsIgnoreCase("list")) {
                    return bdd.JSONStatus(bdd.getJSONList(), true);
                } else if (action.equalsIgnoreCase("participe")) {
                    JSONObject p = (JSONObject) jsonObject.get("participant");
                    return bdd.participe(p);
                } else if (action.equalsIgnoreCase("add")) {
                    JSONObject p = (JSONObject) jsonObject.get("idee");
                    return bdd.addIdee(p);
                } else {
                    return bdd.JSONStatus(new JSONObject(), false);
                }
            }
        } catch (ParseException ex) {
            Logger.getLogger(ServerAT.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            return bdd.JSONStatus(new JSONObject(), false);
        }
        return bdd.JSONStatus(new JSONObject(), true);
    }

}
