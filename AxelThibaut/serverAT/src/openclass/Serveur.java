package openclass;

import java.io.*;
import java.net.*;

public class Serveur {
    public static ServerSocket socket = null;
    public static Thread thread;

    public static void main(String[] args) {
        try {
            socket = new ServerSocket(2009);
            System.out.println("Le serveur est sur le port "+ socket.getLocalPort() + ".");

            thread = new Thread(new Accepter_connexion(socket));
            thread.start();

        } catch (IOException exception) {
            System.err.println("Erreur, le port : "+ socket.getLocalPort()+" est déjà utilisé !");
        }

    }


}

