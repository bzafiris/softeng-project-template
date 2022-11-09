package com.example.app.persistence;

import com.example.app.domain.Journal;
import com.example.app.domain.Researcher;
import com.example.app.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class JPAQueriesTest {

    EntityManager em;

    @BeforeEach
    void setUp() {
        Initializer initializer = new Initializer();
        initializer.prepareData();

        em = JPAUtil.getCurrentEntityManager();
    }

    @Test
    void queryJournals(){

        Query query = em.createQuery("select j from Journal j");
        List<Journal> result = query.getResultList();
        assertEquals(1, result.size());

        Journal j = result.get(0);
        assertNotNull(j.getEditor());
        assertEquals("Avgeriou", j.getEditor().getLastName());

    }

    @Test
    void queryResearchers(){
        Query query = em.createQuery("select r from Researcher r");
        List<Researcher> result = query.getResultList();
        assertEquals(1, result.size());

        Researcher r = result.get(0);
        assertEquals("Nikos", r.getFirstName());
    }

    @Test
    void queryUsers(){
        Query query = em.createQuery("select u from User u");
        List<User> result = query.getResultList();
        assertEquals(2, result.size());
    }
}