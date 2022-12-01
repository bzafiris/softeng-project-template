package com.example.app.representation;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class AuthorRepresentation {

    public Integer id;
    public String firstName;
    public String lastName;
    public String affiliation;
    public String email;

}
