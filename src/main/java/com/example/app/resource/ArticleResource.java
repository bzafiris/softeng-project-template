package com.example.app.resource;

import com.example.app.representation.ArticleMapper;
import com.example.app.representation.ArticleRepresentation;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

@Path("articles")
@RequestScoped
public class ArticleResource {

    @Context
    UriInfo uriInfo;

    @Inject
    ArticleMapper articleMapper;



    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response submitArticle(ArticleRepresentation article){

        URI location = uriInfo.getAbsolutePathBuilder().path("1").build();
        return Response.created(location).build();

    }

}
