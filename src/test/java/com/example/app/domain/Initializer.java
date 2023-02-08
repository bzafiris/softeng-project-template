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
        em.persist(j1);

        em.persist(r1);
        em.persist(r2);
        em.persist(r3);
        em.persist(e1);

        tx.commit();
        em.close();
    }
}
