package com.example.app.representation;

import com.example.app.domain.Editor;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class EditorMapper {

    public abstract EditorRepresentation toRepresentation(Editor entity);

}

