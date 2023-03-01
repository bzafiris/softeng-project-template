package com.example.app.representation;

import com.example.app.domain.Editor;
import com.example.app.domain.Researcher;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class ResearcherMapper {

    @Mapping(target = "firstName", source = "name")
    public abstract ResearcherRepresentation toRepresentation(Researcher entity);

    @Mapping(target = "name", source = "firstName")
    public abstract Researcher toModel(ResearcherRepresentation dto);

}

