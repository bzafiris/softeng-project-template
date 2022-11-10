package com.example.app.domain;

import com.example.app.util.SystemDate;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "score")
    private Integer score;

    @Column(name = "author_comments")
    private String authorComments;

    @Column(name = "editor_comments")
    private String editorComments;

    @Enumerated(EnumType.STRING)
    @Column(name = "recommendation")
    private Recommendation recommendation;

    @Column(name = "submitted")
    private boolean submitted = false;

    @Column(name = "created_at")
    private LocalDate createdAt = SystemDate.now();

    @Column(name = "submitted_at")
    private LocalDate submittedAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invitation_id", nullable = false)
    private ReviewInvitation invitation;

    Review() {
    }

    Review(int score, String authorComments, String editorComments, Recommendation recommendation) {
        this.score = score;
        this.authorComments = authorComments;
        this.editorComments = editorComments;
        this.recommendation = recommendation;
    }

    public Integer getId() {
        return id;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getAuthorComments() {
        return authorComments;
    }

    public void setAuthorComments(String authorComments) {
        this.authorComments = authorComments;
    }

    public String getEditorComments() {
        return editorComments;
    }

    public void setEditorComments(String editorComments) {
        this.editorComments = editorComments;
    }

    public Recommendation getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(Recommendation recommendation) {
        this.recommendation = recommendation;
    }

    public boolean isSubmitted() {
        return submitted;
    }

    public void setSubmitted(boolean submitted) {
        this.submitted = submitted;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(LocalDate submittedAt) {
        this.submittedAt = submittedAt;
    }

    public ReviewInvitation getInvitation() {
        return invitation;
    }

    public void setInvitation(ReviewInvitation invitation) {
        this.invitation = invitation;
        invitation.setReview(this);
    }

    public void submit() throws DomainException {
        if (submitted) {
            throw new DomainException("Review already submitted");
        }

        if (editorComments == null || authorComments == null || recommendation == null
                || score == null) {
            throw new DomainException("Submission of incomplete reviews is not allowed");
        }
        submitted = true;
        submittedAt = SystemDate.now();
    }
}
