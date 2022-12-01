package com.example.app.representation;

import com.example.app.domain.*;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class ArticleMapperTest {

    @Inject
    private ArticleMapper articleMapper;

    private List<Author> getAuthors(){
        return List.of(new Author("Nikos", "Diamantidis", "AUEB", "nad@aueb.gr"),
                new Author("Manolis", "Giakoumakis", "AUEB", "mgia@aueb.gr"));
    }

    private Journal getJournal(){
        return new Journal("Journal of Systems and Software", "0164-1212");
    }

    private Researcher getResearcher(){
        return new Researcher("John", "Doe", "AUEB", "doe@aueb.gr");
    }

    private Author findAuthor(List<Author> authors, String email){
        return authors.stream().filter(a -> a.getEmail().contains(email))
                .findFirst().orElse(null);
    }

    private ArticleRepresentation getArticleRepresentation(){
        ArticleRepresentation dto = new ArticleRepresentation();
        dto.title = "Article title";
        dto.summary = "Article summary";
        dto.keywords = "Article keywords";
        dto.createdAt = LocalDate.of(2022,12, 1);
        dto.journalIssn = "0164-1212";
        return dto;
    }

    private ResearcherRepresentation getResearcherRepresentation(){
        ResearcherRepresentation dto = new ResearcherRepresentation();
        dto.id = 1000;
        dto.firstName = "Nikos";
        dto.lastName = "Diamantidis";
        dto.affiliation = "AUEB";
        dto.email = "ndia@aueb.gr";
        return dto;
    }

    private AuthorRepresentation getAuthorRepresentation(){
        AuthorRepresentation dto = new AuthorRepresentation();
        dto.affiliation = "AUEB";
        dto.firstName = "Manolis";
        dto.lastName = "Giakoumakis";
        dto.email = "mgia@aueb.gr";
        return dto;
    }

    @Transactional
    @Test
    void testToModel(){

        ArticleRepresentation dto = getArticleRepresentation();
        ResearcherRepresentation researcherDto = getResearcherRepresentation();
        dto.researcher = researcherDto;
        AuthorRepresentation authorDto = getAuthorRepresentation();
        dto.authors = new ArrayList<>();
        dto.authors.add(authorDto);

        Article entity = articleMapper.toModel(dto);
        assertEquals(entity.getTitle(), dto.title);
        assertEquals(entity.getKeywords(), dto.keywords);
        assertEquals(entity.getSummary(), dto.summary);
        assertEquals(entity.getCreatedAt(), LocalDate.of(2022, 12, 1));

        Author author = entity.getAuthors().stream().findFirst().get();
        assertEquals(author.getId(), authorDto.id);
        assertEquals(author.getEmail(), authorDto.email);
        assertEquals(author.getFirstName(), authorDto.firstName);
        assertEquals(author.getLastName(), authorDto.lastName);
        assertEquals(author.getAffiliation(), authorDto.affiliation);

        Journal journal = entity.getJournal();
        assertNotNull(journal);
        assertEquals("Journal of Systems and Software", journal.getTitle());

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

        ArticleRepresentation articleRepresentation = articleMapper.toRepresentation(article);
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