/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientat;

import java.io.*;
import java.net.*;

/**
 * @author chronos
 */
public class ClientAT {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Socket smtpSocket = null;      // le socket client
        DataOutputStream os = null; // output stream
        DataInputStream is = null;    // input stream
        try {
            smtpSocket = new Socket("hostname", 9999);
            os = new DataOutputStream(smtpSocket.getOutputStream());
            is = new DataInputStream(smtpSocket.getInputStream());
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: hostname");
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: hostname");
        }
    }
}

