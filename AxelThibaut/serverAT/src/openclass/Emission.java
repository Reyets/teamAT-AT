package openclass;

import java.io.PrintWriter;
import java.util.Scanner;

public class Emission implements Runnable {

    private PrintWriter out;
    private String message = null;
    private Scanner scanner = null;

    public Emission(PrintWriter out) {
        this.out = out;
    }

    public void run() {
        scanner = new Scanner(System.in);
        while(true){
            System.out.println("Votre message : ");
            message = scanner.nextLine();
            out.println(message);
            out.flush();
        }
    }
}
