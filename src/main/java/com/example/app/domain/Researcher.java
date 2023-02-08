package com.example.app.domain;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("RESEARCHER")
public class Researcher extends User {

    public Researcher() {
    }

    public Researcher(String name, String lastName, String affiliation, String email) {
        super(name, lastName, affiliation, email);
    }
}
