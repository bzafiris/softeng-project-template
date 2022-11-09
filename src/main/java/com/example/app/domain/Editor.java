package com.example.app.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("EDITOR")
public class Editor extends User {

    public Editor() {
    }

    public Editor(String firstName, String lastName, String affiliation, String email) {
        super(firstName, lastName, affiliation, email);
    }
}
