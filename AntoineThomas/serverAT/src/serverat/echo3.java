package serverat;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class echo3 {

    public static void main(String[] zero) {

        ServerSocket socketserver  ;
        Socket socketduserveur ;
        BufferedReader in;
        PrintWriter out;

        try {

            socketserver = new ServerSocket(2009);
            System.out.println("Le serveur est à l'écoute du port "+socketserver.getLocalPort());
            socketduserveur = socketserver.accept();
            System.out.println("Un zéro s'est connecté");
            out = new PrintWriter(socketduserveur.getOutputStream());
            out.println("Vous êtes connecté zéro !");
            out.flush();

            socketduserveur.close();
            socketserver.close();

        }catch (IOException e) {

            e.printStackTrace();
        }
    }

}
