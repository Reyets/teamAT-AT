package openclass2;

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class Connexion implements Runnable {

    private Socket socket = null;
    public static Thread t2;
    public static String login = null, pass = null, message1 = null, message2 = null, message3 = null;
    private PrintWriter out = null;
    private BufferedReader in = null;
    private Scanner sc = null;
    private boolean connect = false;

    public Connexion(Socket s){

        socket = s;
    }

    public void run() {

        try {

            out = new PrintWriter(socket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            sc = new Scanner(System.in);

            t2 = new Thread(new Chat_ClientServeur(socket));
            t2.start();

        } catch (IOException e) {

            System.err.println("Le serveur ne répond plus ");
        }
    }

}