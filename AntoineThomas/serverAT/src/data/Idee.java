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
    
    public JSONObject toJSON() {
        JSONObject idee = new JSONObject();
        JSONObject data = new JSONObject();
        data.put("identifiant", identifiant);
        data.put("titre", titre);
        data.put("description", description);
        
        JSONArray techno = new JSONArray();
        for (String techo : technologies) {
            techno.add(techo);
        }
        data.put("technologies", techno);
        
        JSONArray interet = new JSONArray();
        for (Participant parti : interesses) {
            interet.add(parti.toJSON());
        }
        data.put("interesses", interet);
        
        idee.put("idee", data);
        return idee;
    }
    
    public void addInteresse(Participant p) {
        interesses.add(p);
    }


    public int getIdentifiant() {
        return identifiant;
    }

    public ArrayList<Participant> getInteresses() {
        return interesses;
    }
}
