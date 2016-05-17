/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientat;

import java.io.*;
import java.net.*;
import java.util.Scanner;

import org.json.simple.JSONObject;
import requetesmodele.Idee;

/**
 * @author chronos
 */
public class ClientAT {


    private JSONObject commande;

    public static void main(String[] zero) {
        ClientAT client = new ClientAT();
        Socket socks;
        BufferedReader in;
        PrintWriter out;

        try {
            socks = new Socket("localhost", 9999);
            System.out.println("Connecté");
            out = new PrintWriter(socks.getOutputStream());
            while (client.getSaisie()) {
                out.println(client.getCommande().toString());
                out.flush();
                in = new BufferedReader(new InputStreamReader(socks.getInputStream()));
                String message = in.readLine();
                System.out.println(message);
            }
            out.println("quit");
            out.flush();
            socks.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printhelp() {
        System.out.println("Commandes disponibles : ");
        System.out.println("\t- add");
        System.out.println("\t- participe");
        System.out.println("\t- quit");
        System.out.println();
    }

    private boolean getSaisie() {
        printhelp();
        Scanner scan = new Scanner(System.in);

        System.out.println("Entrez votre commande : ");
        switch (scan.nextLine()) {
            case "add":
                commande = creerIdee();
                break;
            case "participe":
                commande = creerParticipation();
                break;
            case "help" :
                printhelp();
                break;
            case "quit":
                return false;
        }

        return true;

    }

    private JSONObject creerIdee() {
        Idee idee = new Idee();
        return idee.toJSON();
    }

    private JSONObject creerParticipation() {
        Scanner scan = new Scanner(System.in);
        System.out.println("----| Participation |----");
        System.out.println("Saisissez l'identifiant de l'idée : ");
        int id = scan.nextInt();
        System.out.println("Saisissez votre adresse email : ");
        String mail = scan.nextLine();

        JSONObject result = new JSONObject();
        JSONObject idee = new JSONObject();
        idee.put("identifiant",id);
        idee.put("mail",mail);
        result.put("idee",idee);
        result.put("request","PARTICIPE");

        return result;
    }


    private JSONObject getCommande() {
        return commande;
    }

}

