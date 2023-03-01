package com.example.app.persistence;

import com.example.app.domain.Recommendation;
import com.example.app.domain.Researcher;
import com.example.app.domain.Review;
import com.example.app.domain.User;
import io.quarkus.panache.common.Sort;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Κλάση με παραδείγματα χρήσης της βιβλιοθήκης Panache
 */
@QuarkusTest
class PanacheRepositoryTest {

    @Inject
    ArticleRepository repository;

    @Inject
    UserRepository userRepository;

    @Inject
    ResearcherRepository researcherRepository;

    @Inject
    ReviewRepository reviewRepository;

    @Test
    void count(){
        long userCount = userRepository.count();
        assertEquals(4, userCount);

        long researcherCount = researcherRepository.count();
        assertEquals(3, researcherCount);
    }

    @Test
    void criteriaCount(){
        long count = userRepository.count("email like ?1", "%\\.ch");
        assertEquals(2, count);
    }

    @Test
    void listAll(){
        List<Researcher> researchers = researcherRepository.listAll();
        assertEquals(3, researchers.size());
    }

    @Test
    void listCriteria(){
        List<Review> reviews = reviewRepository.list("score >= ?1 and authorComments like ?2",
                Sort.ascending("id"),
                30, "Comment%");
        assertEquals(3, reviews.size());
        Review review = reviews.get(0);
        assertEquals(review.getScore(), 60);
    }

    @Test
    void findCriteria(){
        long count = reviewRepository.find("score >= ?1 and authorComments like ?2",
                Sort.ascending("id"),
                30, "Comment%")
                .page(1, 10)
                .count();
        assertEquals(3, count);
    }

    @TestTransaction
    @Transactional
    @Test
    void conditionalUpdate(){

        int updateCount = userRepository.update("password = ?1 where name = ?2", "123", "Nikos");
        assertEquals(updateCount, 1);

        User user = userRepository.find("password = ?1", "123").firstResultOptional().orElse(null);
        assertNotNull(user);
    }

    @Test
    @Transactional
    @TestTransaction
    void conditionalDelete(){
        long deletedCount = reviewRepository.delete("recommendation = ?1", Recommendation.REJECT);
        assertEquals(2, deletedCount);
    }

    @Test
    void listCriteriaOnAssociation(){
        List<Review> reviews = reviewRepository.list("invitation.researcher.name = ?1", "Nikos");
        assertEquals(1, reviews.size());

    }

}