package serverat;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class echo3 {

    public static void main(String[] zero) {
        ServerSocket serv  ;
        Socket socks ;
        BufferedReader in;
        PrintWriter out;
        try {
            serv = new ServerSocket(9999);
            System.out.println("Serveur lancé sur 9999");
            socks = serv.accept();
            System.out.println("Connecté");
            
            out = new PrintWriter(socks.getOutputStream());
            out.flush();
            
            in  = new BufferedReader(new InputStreamReader(socks.getInputStream()));
            String recu = in.readLine();
            
            System.out.println(recu);
            
            socks.close();
            serv.close();

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

}
