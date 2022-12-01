package com.example.app.representation;

public class EditorRepresentation {

    public EditorRepresentation() {
    }

    public EditorRepresentation(String firstName, String lastName, String affiliation, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.affiliation = affiliation;
        this.email = email;
    }

    public String firstName;
    public String lastName;
    public String affiliation;
    public String email;
}
