package com.example.app.domain;

import javax.persistence.*;
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING)
public abstract class User {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Integer id;

    @Column(name="first_name", length=50, nullable=false)
    protected String firstName;

    @Column(name="last_name", length=50, nullable=false)
    protected String lastName;

    @Column(name="affiliation", length=200, nullable=false)
    protected String affiliation;

    @Column(name="email", length=30, nullable=false, unique = true)
    private String email;

    @Column(name="password", length=10)
    private String password;

    protected User() {
    }

    protected User(String firstName, String lastName, String affiliation, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.affiliation = affiliation;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
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

    public void setEmail(String email) {
        this.email = email;
    }
}
