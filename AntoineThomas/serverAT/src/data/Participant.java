package data;

import org.json.simple.JSONObject;

/**
 * Created by Antoine on 03/05/2016.
 */
public class Participant {
    private final int identifiant;
    private final String nom;
    private final String email;

    public Participant(String nom, String email, int identifiant) {
        this.nom = nom;
        this.identifiant = identifiant;
        this.email = email;
    }

    public JSONObject toJSON() {
        JSONObject part = new JSONObject();
        JSONObject data = new JSONObject();
        data.put("identifiant", identifiant);
        data.put("nom", nom);
        data.put("email", email);
        part.put("Participant", data);
        return part;
    }
    
}
