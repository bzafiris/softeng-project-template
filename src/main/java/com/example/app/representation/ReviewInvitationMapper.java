package com.example.app.representation;

import com.example.app.domain.ReviewInvitation;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "cdi",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class ReviewInvitationMapper {

    @Mapping(target = "articleId", source = "model")
    @Mapping(target = "researcherEmail", source = "researcher.email")
    @Mapping(target = "researcherId", expression = "java(model.getResearcher().getId())")
    @Mapping(target = "createdAt", source = "createdAt", qualifiedByName = "dateFormatter")
    public abstract ReviewInvitationRepresentation toRepresentation(
            ReviewInvitation model);

    @Named("dateFormatter")
    public String formatDate(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return date.format(formatter);
    }

    public Integer getArticleId(ReviewInvitation reviewInvitation){
        return reviewInvitation.getArticle().getId();
    }



}
