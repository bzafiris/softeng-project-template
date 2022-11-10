package com.example.app.domain;

import com.example.app.util.SystemDateStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ReviewTest {

    Review review;
    LocalDate now = LocalDate.of(2022, 11, 11);

    @BeforeEach
    void setup(){

        SystemDateStub.setStub(now);
        review = new Review();
        ReviewInvitation invitation = new ReviewInvitation();
        review.setInvitation(invitation);

    }

    @Test
    void allowSubmissionOfCompleteReview() throws Exception {
        review.setScore(50);
        review.setRecommendation(Recommendation.ACCEPT);
        review.setAuthorComments("Comments");
        review.setEditorComments("Comments");
        review.submit();

        assertTrue(review.isSubmitted());
        assertEquals(now, review.getCreatedAt());
        assertEquals(now, review.getSubmittedAt());

    }

    @Test
    void denySubmissionOfIncompleteReview() throws Exception {

        assertThrows(DomainException.class, () -> {
            review.submit();
        });

        assertThrows(DomainException.class, () -> {
           review.setScore(60);
           review.submit();
        });

        assertThrows(DomainException.class, () -> {
            review.setAuthorComments("Comments");
            review.submit();
        });

        assertThrows(DomainException.class, () -> {
            review.setEditorComments("Comments");
            review.submit();
        });

        review.setRecommendation(Recommendation.REJECT);
        review.submit();
        assertTrue(review.isSubmitted());

    }

}