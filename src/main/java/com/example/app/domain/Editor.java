package com.example.app.domain;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("EDITOR")
public class Editor extends User {

    public Editor() {
    }

    public Editor(String name, String lastName, String affiliation, String email) {
        super(name, lastName, affiliation, email);
    }
}
