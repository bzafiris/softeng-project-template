package com.example.app.representation;

import com.example.Fixture;
import com.example.app.domain.ReviewInvitation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReviewInvitationMapperTest {

    ReviewInvitationMapper mapper;
    @BeforeEach
    void setup(){
        mapper = new ReviewInvitationMapperImpl();
    }

    @Test
    void testToRepresentation(){

        ReviewInvitation entity = Fixture.getReviewInvitation();

        ReviewInvitationRepresentation dto = mapper.toRepresentation(entity);
        assertEquals(entity.getAccepted(), dto.accepted);
        assertEquals(entity.getReviewer().getEmail(), dto.researcherEmail);
        assertEquals(entity.getReviewer().getId(), dto.researcherId);
        assertEquals(entity.getArticle().getId(), dto.articleId);
        assertEquals("2022-12-01", dto.createdAt);

    }





}