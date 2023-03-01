package com.example.app.representation;

import com.example.app.domain.Editor;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class EditorMapper {

    @Mapping(target = "firstName", source = "name")
    public abstract EditorRepresentation toRepresentation(Editor entity);

}

