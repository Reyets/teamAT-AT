package data;

import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Created by Antoine on 03/05/2016.
 */
public class BaseDeDonnee {

    private final ArrayList<Idee> idees;

    public BaseDeDonnee() {
        super();
        this.idees = new ArrayList<>();
    }

    public ArrayList<Participant> getParticipantsInteresses(int idIdee) {
        return idees.get(idIdee).getInteresses();
    }

    public ArrayList<Idee> getIdees() {
        return idees;
    }

    public JSONArray addIdee(Idee idee) {
        JSONArray tosend = new JSONArray();
        idees.add(idee);
        tosend.add(JSONStatus(true));
        return tosend;
    }

    public JSONArray addInteresse(Participant p, int id) throws Exception {
        JSONArray tosend = new JSONArray();
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
            tosend.add(JSONStatus(true));
        }
        return tosend;
    }

    public JSONArray getJSONList(int id) throws Exception {
        JSONArray tosend = new JSONArray();

        for (Idee idee : idees) {
            if (idee.getIdentifiant() == id) {
                tosend.add(idee.toJSON());
            }
        }
        if (tosend.isEmpty()) {
            throw new Exception("Idée Non trouvée");
        } else {
            tosend.add(JSONStatus(true));
        }
        return tosend;
    }

    private JSONObject JSONStatus(boolean ok) {
        JSONObject status = new JSONObject();
        if (ok) {
            status.put("status", "OK");
        } else {
            status.put("status", "BAD REQUEST");
        }
        return status;
    }

}
