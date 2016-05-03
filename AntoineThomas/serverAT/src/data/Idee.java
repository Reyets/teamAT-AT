package data;

import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


/**
 * Created by Antoine on 03/05/2016.
 */
public class Idee {
    private final int identifiant;
    private final String titre;
    private final String description;
    private final ArrayList<String> technologies;
    private final ArrayList<Participant> interesses;

    public Idee(int identifiant, String titre, String description, ArrayList<String> technologies, ArrayList<Participant> interesses) {
        this.identifiant = identifiant;
        this.titre = titre;
        this.description = description;
        this.technologies = technologies;
        this.interesses = interesses;
    }
    
    
    
}
