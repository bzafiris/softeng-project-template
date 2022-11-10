package com.example.app.domain;

import com.example.app.util.SystemDateStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ArticleTest {

    Article article;
    LocalDate now = LocalDate.of(2022, 11, 11);
    LocalDate invitationDate = LocalDate.of(2022, 11, 20);
    private ReviewInvitation invitation;
    private Researcher researcher;

    @BeforeEach
    void setUp() throws Exception {

        SystemDateStub.setStub(now);

        article = new Article();
        Author author = new Author();
        author.setEmail("mgia@aueb.gr");
        article.addAuthor(author);

        SystemDateStub.setStub(invitationDate);
        researcher = new Researcher();
        researcher.setEmail("bob@aueb.gr");

        invitation = article.inviteReviewer(researcher);
    }

    @Test
    void inviteNewReviewer() throws Exception {

        assertNotNull(invitation);
        assertEquals(invitationDate, invitation.getCreated_at());
        assertEquals(researcher, invitation.getReviewer());
        assertEquals(article, invitation.getArticle());
        assertNull(invitation.getAccepted());

    }

    @Test
    void inviteDifferentReviewers() throws Exception {

        Researcher mary = new Researcher();
        mary.setEmail("mary@aueb.gr");

        ReviewInvitation invitation = article.inviteReviewer(mary);
        assertNotNull(invitation);

        Set<ReviewInvitation> invitations = article.getReviewInvitations();
        assertEquals(2, invitations.size());

    }
    // expect exception
    @Test()
    void denyDuplicateReviewInvitations(){

        DomainException exception = assertThrows(DomainException.class, () ->{
            // invite the same reviewer twice
            article.inviteReviewer(researcher);
        });

        assertEquals(1, article.getReviewInvitations().size());
        assertEquals("Reviewer already invited", exception.getMessage());

    }
}