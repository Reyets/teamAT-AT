/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Thom
 */
public class BaseDeDonneeTest {
    BaseDeDonnee bdd;
    Idee id;
    Idee idempty;
    public BaseDeDonneeTest() {
        bdd = new BaseDeDonnee();
        ArrayList<String> technos = new ArrayList<>();
        technos.add("JAVA");
        technos.add("JSON");
        ArrayList<Participant> participants = new ArrayList<>();
        participants.add(new Participant("Thomas Jalabert", "thom.jalabert@gmail", 0));
        participants.add(new Participant("Antoine Steyer", "antoine.steyer@gmail", 1));
        id = new Idee(0, "Server Java", "Petit projet client-serveur en java.", technos, participants);
        idempty = new Idee(1, "Server Java2", "Petit projet client-serveur en java.", technos, new ArrayList<>());
    }

    /**
     * Test of addIdee method, of class BaseDeDonnee.
     */
    @Test
    public void testAddIdee() {
        bdd.addIdee(id);
        assertFalse(bdd.getIdees().isEmpty());
    }

    /**
     * Test of getParticipantsInteresses method, of class BaseDeDonnee.
     */
    @Test
    public void testGetParticipantsInteresses() {
        assertTrue(idempty.getInteresses().isEmpty());
        assertFalse(id.getInteresses().isEmpty());
    }
    
     @Test
    public void testgetJSONList() {
        
        System.out.println(bdd.getJSONList());
        bdd.addIdee(id);
        bdd.addIdee(idempty);
        System.out.println(bdd.getJSONList());
    }
    
     @Test
    public void testgetJSONListID() {
        bdd.addIdee(id);
        bdd.addIdee(idempty);
        try {
            System.out.println(bdd.getJSONList(1));
        } catch (Exception ex) {
            Logger.getLogger(BaseDeDonneeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testaddInteresse() {
        bdd.addIdee(id);
        bdd.addIdee(idempty);
        Participant p = new Participant("toto", "email@gmail.com", 5);
        idempty.addInteresse(p);
        System.out.println(idempty.toJSON());
        
    }
    
}
