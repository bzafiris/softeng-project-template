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
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="title", length=200, nullable=false)
    private String title;

    @Column(name="summary", length=4000, nullable=false)
    private String summary;

    @Column(name="keywords", length=500, nullable=false)
    private String keywords;

    @Column(name="created_at")
    private LocalDate created_at = SystemDate.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "journal_id", nullable = false)
    private Journal journal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "correspondent_author_id", nullable = false)
    private Researcher correspondentAuthor;

    public Article() {
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public String getKeywords() {
        return keywords;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Journal getJournal() {
        return journal;
    }

    public void setJournal(Journal journal) {
        this.journal = journal;
    }

    public Researcher getCorrespondentAuthor() {
        return correspondentAuthor;
    }

    public void setCorrespondentAuthor(Researcher correspondentAuthor) {
        this.correspondentAuthor = correspondentAuthor;
    }
}
