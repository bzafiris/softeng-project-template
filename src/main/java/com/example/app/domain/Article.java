package com.example.app.domain;

import com.example.app.util.SystemDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
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
    @JoinColumn(name = "researcher_id", nullable = false)
    private Researcher correspondentAuthor;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "articles_authors", joinColumns = {@JoinColumn(name = "article_id")},
            inverseJoinColumns = {@JoinColumn(name = "author_id")})
    private Set<Author> authors = new HashSet<>();

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
        return new HashSet<>(authors);
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
}