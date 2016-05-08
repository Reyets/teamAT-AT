package serveur;

import java.io.*;
import java.net.*;

/**
 * Auteur : Axel Aiello
 * Class pour accepter la connexion
 */
public class Accepter_connexion implements Runnable{

    private ServerSocket socketserver = null;
    private Socket socket = null;
    public Thread thread1;
    public Accepter_connexion(ServerSocket ss){
        socketserver = ss;
    }

    public void run() {
        try {
            while(true){
                socket = socketserver.accept();
                System.out.println("Un utilisateur est connecté");

                thread1 = new Thread(new Authentification(socket));
                thread1.start();
            }
        } catch (IOException e) {
            System.err.println("ERROR SERVER");
        }
    }

}

