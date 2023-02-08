package com.example.app.persistence;

import com.example.app.domain.Initializer;
import com.example.app.domain.Journal;
import com.example.app.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JPAQueriesTest {

    EntityManager em;

    @BeforeEach
    public void setup(){

        Initializer initializer = new Initializer();
        initializer.prepareData();

        em = JPAUtil.getCurrentEntityManager();
    }

    @Test
    public void listJournals(){

        List<Journal> result = em.createQuery("select j from Journal j")
                .getResultList();

        assertEquals(1, result.size());
        Journal j = result.get(0);
        assertEquals("0164-1212", j.getIssn());
    }

    @Test
    public void listResearchers(){
        List<User> result = em.createQuery("select r from Researcher r")
                .getResultList();

        assertEquals(3, result.size());
    }

    @Test
    public void listUsers(){
        List<User> result = em.createQuery("select u from User u")
                .getResultList();

        assertEquals(4, result.size());
    }

}
