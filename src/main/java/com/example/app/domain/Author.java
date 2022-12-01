package com.example.app.domain;


import javax.persistence.*;

@Entity
@Table(name = "authors")
public class Author {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;

    @Column(name = "affiliation", length = 200, nullable = false)
    private String affiliation;

    @Column(name = "email", length = 50, nullable = false)
    private String email;

    public Author() {
    }

    public Author(String firstName, String lastName, String affiliation, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.affiliation = affiliation;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
