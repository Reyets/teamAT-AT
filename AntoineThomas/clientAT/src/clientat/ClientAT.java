/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientat;

import java.io.*;
import java.net.*;
import org.json.simple.JSONObject;
import requetesmodele.Idee;

/**
 * @author chronos
 */
public class ClientAT {

    public static void main(String[] zero) {
        Socket socks;
        BufferedReader in = null;
        PrintWriter out;
        
        try {
            socks = new Socket("localhost",9999);
            System.out.println("Connecté");            
            out = new PrintWriter(socks.getOutputStream());
     
            JSONObject o = getStandardIdee();
            out.println(o.toString());
            out.flush();
            
            confirm(in, socks);
            socks.close();
            
        }catch (UnknownHostException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void confirm(BufferedReader in, Socket socks) throws IOException {
        in = new BufferedReader(new InputStreamReader(socks.getInputStream()));
        String message = in.readLine();
        System.out.println(message);
            
    }
    
    private static JSONObject  getStandardIdee() {
        return new Idee("titre", "nom" ,"mail", "description").toJSON();
    }
    
    private static JSONObject getStandardList() {
        JSONObject pa = new JSONObject();
        pa.put("request", "list");
        return pa;
    }
    

}

