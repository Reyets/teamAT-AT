package data;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import serverat.ServerAT;

/**
 * Created by Antoine on 03/05/2016.
 */
public class BaseDeDonnee {

    private final ArrayList<Idee> idees;
    private final int idIdee;
    private final int idParticipant;

    public BaseDeDonnee() {
        super();
        this.idees = new ArrayList<>();
        idIdee = 0;
        idParticipant = 0;
    }
    
    

    public ArrayList<Participant> getParticipantsInteresses(int idIdee) {
        return idees.get(idIdee).getInteresses();
    }

    public ArrayList<Idee> getIdees() {
        return idees;
    }

    public JSONObject addIdee(Idee idee) {
        JSONObject tosend = new JSONObject();
        idees.add(idee);
        return JSONStatus(tosend, true);
    }

    public JSONObject addInteresse(Participant p, int id) throws Exception {
        JSONObject tosend = new JSONObject();
        for (Idee idee : idees) {
            if (idee.getIdentifiant() == id) {
                if (idee.getInteresses().contains(p)) {
                    throw new Exception("Participant déja interessé !");
                } else {
                    idee.addInteresse(p);
                }
            }
        }
        if (tosend.isEmpty()) {
            throw new Exception("Idée Non trouvée");
        } else {
            JSONStatus(tosend, true);
        }
        return tosend;
    }

    public JSONObject getJSONList() {
        JSONObject tosent = new JSONObject();
        JSONArray array = new JSONArray();
        for (Idee idee : idees) {
            array.add(idee.toJSON());
        }
        tosent.put("list", array);
        return JSONStatus(tosent, true);
    }

    public JSONObject getJSONList(int id) throws Exception {
        JSONObject tosend = new JSONObject();

        for (Idee idee : idees) {
            if (idee.getIdentifiant() == id) {
                tosend.put("list", idee.toJSON());
            }
        }
        if (tosend.isEmpty()) {
            throw new Exception("Idée Non trouvée");
        } else {
            JSONStatus(tosend, true);
        }
        return tosend;
    }

    public JSONObject JSONStatus(JSONObject o, boolean ok) {
        if (ok) {
            o.put("status", "OK");
        } else {
            o.put("status", "BAD REQUEST");
        }
        return o;
    }

}
