package com.example.app.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("RESEARCHER")
public class Researcher extends User {

    public Researcher() {
    }

    public Researcher(String firstName, String lastName, String affiliation, String email) {
        super(firstName, lastName, affiliation, email);
    }


}
