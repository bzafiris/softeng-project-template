package com.example.app.persistence;

import com.example.app.domain.Article;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class ArticleRepository implements PanacheRepository<Article> {
}
