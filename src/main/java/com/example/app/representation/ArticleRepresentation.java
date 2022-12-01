package com.example.app.representation;


import java.time.LocalDate;
import java.util.List;

public class ArticleRepresentation {

    public Integer id;
    public String title;
    public String summary;
    public String keywords;
    public LocalDate createdAt;
    public String journalIssn;
    public ResearcherRepresentation researcher;
    public List<AuthorRepresentation> authors;

}
