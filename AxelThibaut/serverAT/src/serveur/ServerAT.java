package serveur;

import java.io.*;
import java.net.*;

/**
 * Auteur : Axel Aiello
 * Class pour lancer le serveur
 */
public class ServerAT {
    public static ServerSocket socket = null;
    public static Thread thread;

    public ServerAT() {
        // Essaye de ce mettre sur le port 2009
        try {
            socket = new ServerSocket(2009);
            System.out.println("Le serveur est sur le port "+ socket.getLocalPort() + ".");

            thread = new Thread(new Accepter_connexion(socket));
            thread.start();

        }
        // Erreur car déjà utilisé
        catch (IOException exception) {
            System.err.println("Erreur, le port est déjà utilisé !");
        }
    }

    public static void main(String[] args) {
        new ServerAT();
    }


}

