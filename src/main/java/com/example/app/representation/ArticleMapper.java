package com.example.app.representation;

import com.example.app.domain.Article;
import com.example.app.domain.Journal;
import com.example.app.persistence.JournalRepository;
import org.mapstruct.*;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "cdi",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {ResearcherMapper.class, AuthorMapper.class})
public abstract class ArticleMapper {

    @Inject
    JournalRepository journalRepository;

    @Mapping(target = "journalIssn", source = "journal.issn")
    @Mapping(target = "researcher", source = "correspondentAuthor")
    @Mapping(target = "createdAt", source = "createdAt", qualifiedByName = "dateFormatter")
    public abstract ArticleRepresentation toRepresentation(Article entity);

    @Mapping(target = "correspondentAuthor", source = "researcher")
    @Mapping(target = "reviewInvitations", ignore = true)
    public abstract Article toModel(ArticleRepresentation dto);

    @AfterMapping
    public void resolveJournalByIssn(ArticleRepresentation dto, @MappingTarget Article article){

        Journal journal = null;
        if (dto.journalIssn != null){
            journal = journalRepository.find("issn", dto.journalIssn)
                    .firstResultOptional().orElse(null);
        }
        article.setJournal(journal);
    }

    @Named("dateFormatter")
    public String formatDate(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return date.format(formatter);
    }
}


