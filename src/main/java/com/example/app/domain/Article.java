package com.example.app.domain;

import com.example.app.util.SystemDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "articles")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="title", length=200, nullable=false)
    private String title;

    @Column(name="summary", length=4000, nullable=false)
    private String summary;

    @Column(name="keywords", length=500, nullable=false)
    private String keywords;

    @Column(name="submission_date")
    private LocalDate submissionDate = SystemDate.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "journal_id", nullable = false)
    private Journal journal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "correspondent_author_id", nullable = false)
    private Researcher correspondentAuthor;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "article_authors", joinColumns = {@JoinColumn(name = "article_id")},
            inverseJoinColumns = {@JoinColumn(name = "author_id")})
    private Set<Author> authors = new HashSet<>();

    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<ReviewInvitation> reviewInvitations = new ArrayList<>();

    @Column(name = "created_at")
    private LocalDate createdAt = SystemDate.now();

    public Article() {
    }

    public Article(String title, String summary, String keywords) {
        this.title = title;
        this.summary = summary;
        this.keywords = keywords;
    }

    public Journal getJournal() {
        return journal;
    }

    public void setJournal(Journal journal) {
        this.journal = journal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public LocalDate getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(LocalDate submissionDate) {
        this.submissionDate = submissionDate;
    }

    public Researcher getCorrespondentAuthor() {
        return correspondentAuthor;
    }

    public void setCorrespondentAuthor(Researcher correspondentAuthor) {
        this.correspondentAuthor = correspondentAuthor;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void addAuthor(Author author){
        if (author != null){
            authors.add(author);
        }
    }

    public void removeAuthor(Author author){
        if (author != null){
            authors.remove(author);
        }
    }

    public ReviewInvitation inviteReviewer(Researcher r){

        // should not be already invited
        for(ReviewInvitation inv: reviewInvitations){
            if (inv.getResearcher().equals(r)){
                // better throw application level exception
                return null;
            }
        }
        // should not be the correspondent author
        if (this.correspondentAuthor.equals(r)){
            return null;
        }

        ReviewInvitation invitation = new ReviewInvitation(r, this);
        reviewInvitations.add(invitation);

        return invitation;
    }

    public Review createReview(Researcher reviewer) {
        // check invitation exists
        for(ReviewInvitation invitation: reviewInvitations){
            if (invitation.getResearcher().equals(reviewer)){
                Review review = new Review();
                review.setInvitation(invitation);
                invitation.setReview(review);
                review.setCreatedAt(SystemDate.now());
                return review;
            }
        }
        return null;
    }

    public List<ReviewInvitation> getReviewInvitations() {
        return new ArrayList<>(reviewInvitations);
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
}
