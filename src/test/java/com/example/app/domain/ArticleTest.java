package com.example.app.domain;

import com.example.app.util.SystemDate;
import com.example.app.util.SystemDateStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ArticleTest {

    private Article article;
    private Researcher reviewer1;
    private static final LocalDate NOW = LocalDate.of(2023, 2, 8);
    private Researcher author;

    @BeforeEach
    void setUp() {

        SystemDateStub.setStub(NOW);

        article = new Article();
        author = new Researcher();
        author.setEmail("a@aueb.gr");

        reviewer1 = new Researcher();
        reviewer1.setEmail("b@aueb.gr");

        article.setCorrespondentAuthor(author);

    }

    @Test
    public void inviteReviewer(){
        ReviewInvitation invitation = article.inviteReviewer(reviewer1);
        assertNotNull(invitation);
        assertEquals(NOW, invitation.getCreatedAt());
        assertEquals(reviewer1, invitation.getResearcher());
        assertEquals(article, invitation.getArticle());
    }

    @Test
    public void denyDuplicateInvitations(){

        Researcher reviewer2 = new Researcher();
        reviewer2.setEmail("b@aueb.gr");

        ReviewInvitation invitation = article.inviteReviewer(reviewer1);
        assertNotNull(invitation);

        ReviewInvitation invitation2 = article.inviteReviewer(reviewer2);
        assertNull(invitation2);
    }

    @Test
    public void denyInvitationToCorrespondentAuthor(){
        ReviewInvitation invitation = article.inviteReviewer(author);
        assertNull(invitation);
    }
}