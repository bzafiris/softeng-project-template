package com.example.app.resource;

import com.example.app.domain.Article;
import com.example.app.persistence.ArticleRepository;
import com.example.app.representation.ArticleMapper;
import com.example.app.representation.ArticleRepresentation;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
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

    @Inject
    ArticleRepository articleRepository;



    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response submitArticle(ArticleRepresentation articleDto){

        Article entity = articleMapper.toModel(articleDto);
        articleRepository.persist(entity);

        URI location = uriInfo.getAbsolutePathBuilder().path(
                Integer.toString(entity.getId())).build();
        return Response
                .created(location)
                .entity(articleMapper.toRepresentation(entity))
                .build();

    }

}
