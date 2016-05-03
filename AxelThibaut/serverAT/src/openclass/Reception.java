package openclass;

import java.io.BufferedReader;
import java.io.IOException;

public class Reception implements Runnable {

    private BufferedReader in;
    private String message = null;
    private String login = null;

    public Reception(BufferedReader in, String login){
        this.in = in;
        this.login = login;
    }

    public void run() {
        while(true){
            try {
                message = in.readLine();
                System.out.println(login + " : " + message);
                /**
                 * Passeur des Commandes
                 *
                 * Si ADD -> ajouter dans le fichier idée.txt
                 * Si LIST -> Lire la liste des idées dans le fichier idée.txt
                 * Si LIST[] -> Lire la liste des participants participant au une idée
                 * Si Participe -> se rajoute à la liste
                 *
                 */
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }

}
