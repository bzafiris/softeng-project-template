package com.example.app.domain;

import com.example.app.persistence.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.swing.text.html.parser.Entity;
import javax.transaction.UserTransaction;

public class Initializer {

    protected void eraseData(){
        EntityManager em = JPAUtil.getCurrentEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        em.createNativeQuery("delete from articles")
                .executeUpdate();

        em.createNativeQuery("delete from journals")
                .executeUpdate();

        em.createNativeQuery("delete from users")
                .executeUpdate();

        tx.commit();
        em.close();

    }

    public void prepareData(){
        eraseData();

        EntityManager em = JPAUtil.getCurrentEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Author a11 = new Author("Pooja", "Rani", "University of Bern", "pooja.rani@unibe.ch");
        Author a12 = new Author("Ariana", "Blasi", "Università della Svizzera italiana", "arianna.blasi@usi.ch");

        Author a21 = new Author("Enrico", "Fregnan", "University of Zurich", "fregnan@ifi.uzh.ch");
        Author a22 = new Author("Josua", "Fröhlich", "University of Zurich", "josua.froehlich@uzh.ch");

        Researcher r1 = new Researcher("Nikos", "Diamantidis", "AUEB", "ndia@aueb.gr");
        r1.setPassword("123");

        Researcher r2 = new Researcher("Pooja", "Rani", "University of Bern", "pooja.rani@unibe.ch");
        r2.setPassword("123");
        Researcher r3 = new Researcher("Enrico", "Fregnan", "University of Zurich", "fregnan@ifi.uzh.ch");
        r3.setPassword("123");

        Editor e1 = new Editor("Paris", "Avgeriou",
                "University of Groningen", "avgeriou@gmail.com");
        e1.setPassword("123");

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
        a1.setCorrespondentAuthor(r2);

        Article a2 = new Article();
        a2.setTitle("Graph-based visualization of merge requests for code review");
        a2.setSummary("Code review is a software development practice aimed at assessing " +
                "code quality, finding defects, and sharing knowledge among developers ...");
        a2.setKeywords("Modern code review\n" +
                "Software visualization\n" +
                "Empirical software engineering");
        a2.setJournal(j1);
        a2.setCorrespondentAuthor(r3);

        em.persist(r1);
        em.persist(r2);
        em.persist(r3);
        em.persist(e1);

        em.persist(j1);

        em.persist(a1);
        em.persist(a2);

        tx.commit();
        em.close();
    }
}
