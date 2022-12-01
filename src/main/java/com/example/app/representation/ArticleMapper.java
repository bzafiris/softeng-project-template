package com.example.app.representation;

import com.example.app.domain.Article;
import com.example.app.domain.Journal;
import com.example.app.persistence.JournalRepository;
import org.mapstruct.*;

import javax.inject.Inject;

@Mapper(componentModel = "cdi",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class ArticleMapper {

    @Inject
    JournalRepository journalRepository;

    @Mapping(target = "journalIssn", source = "journal.issn")
    @Mapping(target = "researcher", source = "correspondentAuthor")
    public abstract ArticleRepresentation toRepresentation(Article entity);

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
}


