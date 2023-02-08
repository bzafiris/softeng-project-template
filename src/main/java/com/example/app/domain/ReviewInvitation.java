package com.example.app.domain;

import com.example.app.util.SystemDate;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "review_invitations")
public class ReviewInvitation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "invitation_date", nullable = false)
    private LocalDate invitationDate = SystemDate.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "researcher_id")
    private Researcher researcher;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;

    @Column(name = "accepted")
    private Boolean accepted = null;

    public ReviewInvitation() {
    }

    public ReviewInvitation(Researcher researcher, Article article) {
        this.researcher = researcher;
        this.article = article;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getInvitationDate() {
        return invitationDate;
    }

    public void setInvitationDate(LocalDate invitationDate) {
        this.invitationDate = invitationDate;
    }

    public Researcher getResearcher() {
        return researcher;
    }

    public void setResearcher(Researcher researcher) {
        this.researcher = researcher;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }

}
