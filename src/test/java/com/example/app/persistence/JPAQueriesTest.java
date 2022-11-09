package com.example.app.persistence;

import com.example.app.domain.Journal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

    }
}