package com.example.app.representation;

import com.example.app.domain.Article;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class ArticleMapper {

    @Mapping(target = "journalIssn", source = "journal.issn")
    @Mapping(target = "researcher", source = "correspondentAuthor")
    public abstract ArticleRepresentation toRepresentation(Article domain);

}
