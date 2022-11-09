package com.example.app.persistence;

import com.example.app.domain.*;

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

        Query query = em.createNativeQuery("delete from articles");
        query.executeUpdate();

        query = em.createNativeQuery("delete from journals");
        query.executeUpdate();

        query = em.createNativeQuery("delete from users");
        query.executeUpdate();

        query = em.createNativeQuery("delete from authors");
        query.executeUpdate();


        tx.commit();
        em.close();
    }
    

    public void prepareData() {

        // πριν εισάγουμε τα δεδομένα διαγράφουμε ότι υπάρχει
        eraseData();                      



        Researcher r1 = new Researcher("Nikos", "Diamantidis", "AUEB", "ndia@aueb.gr");

        Editor e1 = new Editor("Paris", "Avgeriou",
                "University of Groningen", "avgeriou@gmail.com");

        Journal j1 = new Journal("Journal of Systems and Software", "0164-1212");
        j1.setEditor(e1);

        Article a1 = new Article();
        a1.setTitle("A decade of code comment quality assessment: A systematic literature review");
        a1.setSummary("Code comments are important artifacts in software systems and play" +
                " a paramount role in many software engineering (SE) tasks...");
        a1.setKeywords("Code comments\n" +
                "Documentation quality\n" +
                "Systematic literature review");
        a1.setJournal(j1);

        Article a2 = new Article();
        a2.setTitle("Graph-based visualization of merge requests for code review");
        a2.setSummary("Code review is a software development practice aimed at assessing " +
                "code quality, finding defects, and sharing knowledge among developers ...");
        a2.setKeywords("Modern code review\n" +
                "Software visualization\n" +
                "Empirical software engineering");
        a2.setJournal(j1);

        EntityManager em = JPAUtil.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        em.persist(e1);
        em.persist(j1);
        em.persist(r1);
        em.persist(a1);
        em.persist(a2);

        tx.commit();
        em.close();
    }    
}
