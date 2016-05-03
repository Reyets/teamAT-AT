package data;

import java.util.ArrayList;

/**
 * Created by Antoine on 03/05/2016.
 */
public class Idee {
    private String titre;
    private String description;
    private ArrayList<String> technologies;
    private ArrayList<Participant> interesses;

    public Idee(String titre, String description, ArrayList<String> technologies, ArrayList<Participant> interesses) {
        this.titre = titre;
        this.description = description;
        this.technologies = technologies;
        this.interesses = interesses;
    }

}
