package openclass2;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;


public class Reception implements Runnable {

    private BufferedReader in;
    private String message = null;

    public Reception(BufferedReader in){

        this.in = in;
    }

    public static void displayList(String answer){
        JSONObject ans =  new JSONObject(answer);
        String status = ans.getString("status");
        if(status.compareTo("OK")==0){
            JSONArray array = ans.getJSONArray("list");
            System.out.println("liste des personnes interressées : ");
            for (int i = 0; i < array.length(); i++) {
                System.out.println(array.get(i));
            }
        }
        System.out.println(status);
    }

    public void run() {

        while(true){
            try {

                message = in.readLine();

            } catch (IOException e) {

                e.printStackTrace();
            }



            JSONObject answer =  new JSONObject(message);
            String status = answer.getString("status");



            if(status.equals("OK")){
                if(answer.has("list")) {//check if list exist
                    JSONArray list = answer.getJSONArray("list");
                    for (int i = 0; i < list.length(); i++) {
                        JSONObject idea = list.getJSONObject(i).getJSONObject("idee");
                        //System.out.println(idea);
                        System.out.println("titre : " + idea.getString("titre"));
                        System.out.println("description : " + idea.getString("description"));
                        System.out.println("technologies : " + idea.getJSONArray("technologies"));
                        //System.out.println("participants : " + idea.getJSONArray("interesses"));
                    }
                }
            }

            System.out.println(status);

/*
                message = in.readLine();
                System.out.println(message);
*/
        }
    }

}