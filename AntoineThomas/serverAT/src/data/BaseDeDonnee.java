package data;

import java.util.ArrayList;

/**
 * Created by Antoine on 03/05/2016.
 */
public class BaseDeDonnee {
    private ArrayList<Idee> idees;
    private ArrayList<Participant> participants;

    public BaseDeDonnee() {
        this.idees = new ArrayList<>();
        this.participants = new ArrayList<>();
    }

    public BaseDeDonnee(ArrayList<Idee> idees, ArrayList<Participant> participants) {
        super();
        this.idees = idees;
        this.participants = participants;
    }

    public void addIdee(Idee newIdee) {
        idees.add(newIdee);
    }

    public void addParticipant(Participant newParticipant) {
        participants.add(newParticipant);
    }

    public ArrayList<Idee> getIdees() {
        return idees;
    }

    public ArrayList<Participant> getParticipants() {
        return participants;
    }

    public ArrayList<Participant> getParticipantsInteresses(int idIdee) {
        return idees.get(idIdee).getInteresses();
    }


}
