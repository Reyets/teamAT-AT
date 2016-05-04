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
    
    public void addIdee(Idee newIdee) {
        idees.add(newIdee);
    }

    public ArrayList<Participant> getParticipantsInteresses(int idIdee) {
        return idees.get(idIdee).getInteresses();
    }

    public ArrayList<Idee> getIdees() {
        return idees;
    }
    
    public JSONArray getJSONList() {
        JSONArray tosend = new JSONArray();
        JSONArray list = new JSONArray();
        for(Idee idee : idees) {
            list.add(idee.toJSON());
        }
        tosend.add(JSONStatus(true));
        tosend.add(list);
        return tosend;
    }
    
    public JSONArray getJSONList(int id) throws Exception {
        JSONArray tosend = new JSONArray();
        
        for(Idee idee : idees) {
            if(idee.getIdentifiant()==id) {
                tosend.add(idee.toJSON());
            }            
        }
        if(tosend.isEmpty()) {
             throw new Exception("Idée Non trouvée");
        } else {
            tosend.add(JSONStatus(true));
        }       
       
        return tosend;
    }

    private JSONObject JSONStatus(boolean ok) {
        JSONObject status = new JSONObject();
        if(ok) {
            status.put("status", "OK");
        } else {
            status.put("status", "BAD REQUEST");
        }
        return status;
    }


}
