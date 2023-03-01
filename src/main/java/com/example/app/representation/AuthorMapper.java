package com.example.app.representation;

import com.example.app.domain.Author;
import com.example.app.domain.Editor;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class AuthorMapper {

    @Mapping(target = "firstName",  source = "name")
    public abstract AuthorRepresentation toRepresentation(Author entity);

    @Mapping(target = "name",  source = "firstName")
    public abstract Author toModel(AuthorRepresentation dto);

}

