package com.example.app.persistence;

import com.example.app.domain.Journal;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class JournalRepository implements PanacheRepository<Journal> {

}
