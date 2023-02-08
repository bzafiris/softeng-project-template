package com.example.app.persistence;

import com.example.app.domain.Article;
import com.example.app.domain.Initializer;
import com.example.app.domain.Journal;
import com.example.app.domain.User;
import com.example.app.util.SystemDate;
import com.example.app.util.SystemDateStub;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class JPAQueriesTest {

    EntityManager em;

    @BeforeEach
    public void setup(){

        SystemDateStub.setStub(LocalDate.of(2023, 2, 8));

        Initializer initializer = new Initializer();
        initializer.prepareData();

        em = JPAUtil.getCurrentEntityManager();
    }

    @AfterEach
    public void tearDown(){
        SystemDateStub.reset();
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
    public void fetchEditorWithJournal(){

        List<Journal> result = em.createQuery("select j from Journal j join fetch j.editor e")
                .getResultList();

        em.close();

        assertEquals(1, result.size());
        Journal j = result.get(0);
        assertEquals("0164-1212", j.getIssn());
        assertEquals("Avgeriou", j.getEditor().getLastName());
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

    @Test
    public void listArticles(){
        List<Article> result = em.createQuery("select a from Article a")
                .getResultList();

        assertEquals(2, result.size());

        Article a = result.get(0);
        assertEquals(LocalDate.of(2023, 2, 8), a.getSubmissionDate());
    }

    @Test
    public void listArticlesFetchDependencies(){

        List<Article> result = em.createQuery("select distinct a from Article a " +
                        "   join fetch a.journal j" +
                        "   join fetch a.correspondentAuthor r " +
                        "   join fetch a.authors ")
                .getResultList();

        assertEquals(2, result.size());

        em.close();

        Article a = result.get(0);
        assertNotNull(a.getJournal().getTitle());
        assertNotNull(a.getCorrespondentAuthor().getLastName());
        assertEquals(2, a.getAuthors().size());

    }

    @Test
    public void listArticleWithReviewInvitations(){
        List<Article> result = em.createQuery("select distinct a from Article a " +
                        "   join fetch a.reviewInvitations r " +
                        "   where a.correspondentAuthor.lastName = :lastName")
                .setParameter("lastName", "Rani")
                .getResultList();

        em.close();

        assertEquals(1, result.size());
        assertEquals(2, result.get(0).getReviewInvitations().size());

    }

}
