package com.example.app.representation;

import com.example.app.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class ArticleMapperTest {

    private ArticleMapper mapper;

    @BeforeEach
    public void setup(){
        mapper = new ArticleMapperImpl();
    }

    private List<Author> getAuthors(){
        return List.of(new Author("Nikos", "Diamantidis", "AUEB", "nad@aueb.gr"),
                new Author("Manolis", "Giakoumakis", "AUEB", "mgia@aueb.gr"));
    }

    private Journal getJournal(){
        return new Journal("Journal title", "1234");
    }

    private Researcher getResearcher(){
        return new Researcher("John", "Doe", "AUEB", "doe@aueb.gr");
    }

    private Author findAuthor(List<Author> authors, String email){
        return authors.stream().filter(a -> a.getEmail().contains(email))
                .findFirst().orElse(null);
    }

    @Test
    void testToRepresentation(){

        Article article = new Article(1, "Title", "Summary", "Keywords",
                LocalDate.of(2022, 12, 1));

        Researcher researcher = getResearcher();
        article.setCorrespondentAuthor(researcher);
        article.setJournal(getJournal());
        List<Author> authors = getAuthors();
        article.addAuthor(authors.get(0));
        article.addAuthor(authors.get(1));

        ArticleRepresentation articleRepresentation = mapper.toRepresentation(article);
        assertEquals(article.getTitle(), articleRepresentation.title);
        assertEquals(article.getId(), articleRepresentation.id);
        assertEquals(article.getSummary(), articleRepresentation.summary);
        assertEquals(article.getKeywords(), articleRepresentation.keywords);
        assertEquals(article.getCreatedAt(), articleRepresentation.createdAt);
        assertEquals(article.getJournal().getIssn(), articleRepresentation.journalIssn);

        assertEquals(researcher.getFirstName(), articleRepresentation.researcher.firstName);
        assertEquals(researcher.getLastName(), articleRepresentation.researcher.lastName);
        assertEquals(researcher.getAffiliation(), articleRepresentation.researcher.affiliation);
        assertEquals(researcher.getEmail(), articleRepresentation.researcher.email);

        assertEquals(2, articleRepresentation.authors.size());
        for(AuthorRepresentation r: articleRepresentation.authors){
            Author d = findAuthor(authors, r.email);
            assertEquals(d.getFirstName(), r.firstName);
            assertEquals(d.getLastName(), r.lastName);
            assertEquals(d.getAffiliation(), r.affiliation);
        }

    }

}