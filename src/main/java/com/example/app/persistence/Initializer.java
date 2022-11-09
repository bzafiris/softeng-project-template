package com.example.app.persistence;

import com.example.app.domain.Journal;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;


/**
 * Κλάση που αναλαμβάνει να αρχικοποιήσει τα δεδομένα της βάσης δεδομένων<p>
 * Βοηθητική κλάση που παρέχει δεδομένα για τα παραδείγματα και τις δοκιμασίες ελέγχου<p>
 */
public class Initializer {

    //διαγράφουμε όλα τα δεδομένα στη βάση δεδομένων
    public void  eraseData() {
        EntityManager em = JPAUtil.getCurrentEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        
        Query query = em.createNativeQuery("delete from journals");
        query.executeUpdate();

        tx.commit();
        em.close();
    }
    

    public void prepareData() {

        // πριν εισάγουμε τα δεδομένα διαγράφουμε ότι υπάρχει
        eraseData();                      

        Journal j1 = new Journal("Journal of Systems and Software", "0164-1212");
        EntityManager em = JPAUtil.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        em.persist(j1);
        tx.commit();
        em.close();
    }    
}
