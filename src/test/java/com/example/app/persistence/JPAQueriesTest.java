package com.example.app.persistence;

import com.example.app.domain.Article;
import com.example.app.domain.Journal;
import com.example.app.domain.Researcher;
import com.example.app.domain.User;
import com.example.app.util.SystemDate;
import com.example.app.util.SystemDateStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class JPAQueriesTest {

    EntityManager em;
    LocalDate now = LocalDate.of(2022, 11, 11);

    @BeforeEach
    void setUp() {

        SystemDateStub.setStub(now);
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
        assertEquals(3, result.size());

    }

    @Test
    void queryUsers(){
        Query query = em.createQuery("select u from User u");
        List<User> result = query.getResultList();
        assertEquals(4, result.size());
    }

    @Test
    void queryArticles(){
        Query query = em.createQuery("select a from Article a");
        List<Article> result = query.getResultList();
        assertEquals(2, result.size());

        Article a = result.get(0);
        assertNotNull(a.getJournal());
        assertEquals("Journal of Systems and Software", a.getJournal().getTitle());
        assertNotNull(a.getTitle());
        assertEquals(now, a.getCreated_at());
        assertNotNull(a.getCorrespondentAuthor());

    }
}