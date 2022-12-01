package com.example.app.persistence;

import com.example.app.domain.Researcher;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class ResearcherRepository implements PanacheRepository<Researcher> {
}
