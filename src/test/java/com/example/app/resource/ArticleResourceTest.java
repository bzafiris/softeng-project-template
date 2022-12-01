package com.example.app.resource;

import com.example.Fixture;
import com.example.app.representation.ArticleRepresentation;
import com.example.app.util.SystemDateStub;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;
import java.time.LocalDate;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class ArticleResourceTest {

    @Test
    void submitArticle() {

        SystemDateStub.setStub(LocalDate.of(2022, 12, 1));
        ArticleRepresentation articleRepresentation = Fixture.getArticleRepresentation();
        articleRepresentation.createdAt = null;

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
        assertEquals("2022-12-01", savedArticle.createdAt);

    }

    @Test
    @TestTransaction
    void removeExistingArticle(){

        when()
                .delete(Fixture.API_ROOT + "/articles/4002")
                .then()
                .statusCode(Response.Status.NO_CONTENT.getStatusCode());
    }

    @Test
    @TestTransaction
    void removeNonExistingArticle(){

        when()
                .delete(Fixture.API_ROOT + "/articles/5000")
                .then()
                .statusCode(Response.Status.NOT_FOUND.getStatusCode());
    }
}