package com.example.app.persistence;

import com.example.app.domain.Article;
import com.example.app.domain.Review;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class ReviewRepository implements PanacheRepository<Review> {
}
