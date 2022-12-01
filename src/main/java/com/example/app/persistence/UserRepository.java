package com.example.app.persistence;

import com.example.app.domain.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class UserRepository implements PanacheRepository<User> {
}
