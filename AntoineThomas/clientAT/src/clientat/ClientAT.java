/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientat;

import java.io.*;
import java.net.*;
import org.json.simple.JSONObject;

/**
 * @author chronos
 */
public class ClientAT {

    public static void main(String[] zero) {
        Socket socks;
        BufferedReader in;
        PrintWriter out;
        
        try {
            socks = new Socket("localhost",9999);
            System.out.println("Connecté");            
            out = new PrintWriter(socks.getOutputStream());
            JSONObject o = new JSONObject();
            o.put("action", "list");
            out.println(o.toJSONString());
            out.flush();
            in = new BufferedReader(new InputStreamReader(socks.getInputStream()));
            String message = in.readLine();
            System.out.println(message);
            socks.close();
        }catch (UnknownHostException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private JSONObject getAction(String ask) throws Exception {
        JSONObject action = new JSONObject();
        if(ask.equalsIgnoreCase("list")) {
            action.put("action", "LIST");
        } else if(ask.equalsIgnoreCase("participe")) {
            action.put("action", "PARTICIPE");
        } else if(ask.equalsIgnoreCase("add")) {
            action.put("action", "ADD");
        } else {
            throw new Exception("Unknown action");
        }
        return action;
    }

}

