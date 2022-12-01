package com.example.app.representation;

import com.example.app.domain.Editor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class EditorMapperTest {

    private EditorMapper mapper;

    @BeforeEach
    public void setup(){
        mapper = Mappers.getMapper(EditorMapper.class);
    }

    @Test
    public void testToRepresentation(){

        Editor editor = new Editor("John", "Doe", "AUEB", "doe@aueb.gr");
        editor.setPassword("123");

        EditorRepresentation editorRepresentation = mapper.toRepresentation(editor);

        assertEquals(editor.getFirstName(), editorRepresentation.firstName);
        assertEquals(editor.getLastName(), editorRepresentation.lastName);
        assertEquals(editor.getAffiliation(), editorRepresentation.affiliation);
        assertEquals(editor.getEmail(), editorRepresentation.email);
    }

}