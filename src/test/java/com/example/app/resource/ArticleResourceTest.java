package com.example.app.resource;

import com.example.Fixture;
import com.example.app.representation.ArticleRepresentation;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class ArticleResourceTest {

    @Test
    void submitArticle() {

        ArticleRepresentation articleRepresentation = Fixture.getArticleRepresentation();

        ArticleRepresentation savedArticle = given()
                .contentType(ContentType.JSON)
                .body(articleRepresentation)
                .when()
                .post(Fixture.API_ROOT + "/articles")
                .then().statusCode(201)
                .extract().as(ArticleRepresentation.class);

        assertEquals(1000, savedArticle.researcher.id);
        assertEquals(1, savedArticle.authors.size());
        assertNotNull(savedArticle.authors.get(0).id);

    }
}