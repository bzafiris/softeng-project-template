package com.example.app.domain;

import com.example.app.util.SystemDate;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "review_invitations")
public class ReviewInvitation {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewer_id", nullable = false)
    private Researcher reviewer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id", nullable = false)
    private Article article;

    @Column(name="created_at", nullable = false)
    private LocalDate created_at = SystemDate.now();

    @Column(name = "accepted")
    private Boolean accepted = null;

    public ReviewInvitation() {
    }

    public Researcher getReviewer() {
        return reviewer;
    }

    public void setReviewer(Researcher reviewer) {
        this.reviewer = reviewer;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Integer getId() {
        return id;
    }

    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }

    public void accept(){
        accepted = true;
    }

    public void reject(){
        accepted = false;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
    }
}
