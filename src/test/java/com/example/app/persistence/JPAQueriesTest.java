package com.example.app.persistence;

import com.example.app.domain.*;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
class JPAQueriesTest {

    @Inject
    EntityManager em;

    @Test
    @TestTransaction
    void queryJournals(){

        Query query = em.createQuery("select j from Journal j");
        List<Journal> result = query.getResultList();
        assertEquals(1, result.size());

        Journal j = result.get(0);
        assertNotNull(j.getEditor());
        assertEquals("Avgeriou", j.getEditor().getLastName());

    }

    @Test
    @TestTransaction
    void queryResearchers(){
        Query query = em.createQuery("select r from Researcher r");
        List<Researcher> result = query.getResultList();
        assertEquals(3, result.size());

    }

    @Test
    @TestTransaction
    void queryUsers(){
        Query query = em.createQuery("select u from User u");
        List<User> result = query.getResultList();
        assertEquals(4, result.size());
    }

    @Test
    @TestTransaction
    void queryArticles(){
        Query query = em.createQuery("select a from Article a");
        List<Article> result = query.getResultList();
        assertEquals(3, result.size());

        Article a = result.get(0);
        assertNotNull(a.getJournal());
        assertEquals("Journal of Systems and Software", a.getJournal().getTitle());
        assertNotNull(a.getTitle());
        assertEquals(LocalDate.of(2022, 11, 24), a.getCreatedAt());
        assertNotNull(a.getCorrespondentAuthor());

        Set<Author> authors = a.getAuthors();
        assertEquals(2, authors.size());

        Set<ReviewInvitation> reviewInvitations = a.getReviewInvitations();
        assertEquals(1, reviewInvitations.size());

    }

    @Test
    @TestTransaction
    void queryReviews(){
        Query query = em.createQuery("select r from Review r");
        List<Review> result = query.getResultList();
        assertEquals(1, result.size());

        Review review = result.get(0);
        assertNotNull(review.getInvitation());
        assertEquals(LocalDate.of(2022, 11, 1), review.getCreatedAt());
        assertEquals(60, review.getScore());
        assertEquals(Recommendation.ACCEPT, review.getRecommendation());
    }
}