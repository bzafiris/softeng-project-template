package com.example.app.representation;


import io.quarkus.runtime.annotations.RegisterForReflection;

import java.time.LocalDate;
import java.util.List;

@RegisterForReflection
public class ArticleRepresentation {

    public Integer id;
    public String title;
    public String summary;
    public String keywords;
    public String createdAt;
    public String journalIssn;
    public ResearcherRepresentation researcher;
    public List<AuthorRepresentation> authors;

}
