package com.example.app.domain;

import javax.persistence.*;

@Entity
@Table(name = "books")
public class Account {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "number", length = 10)
    private String accountNumber;

    public Account() {
    }

    public Account(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Integer getId() {
        return id;
    }

    
}
