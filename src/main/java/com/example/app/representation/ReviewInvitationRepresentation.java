package com.example.app.representation;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class ReviewInvitationRepresentation {

    public Integer id;
    public Integer researcherId;
    public String researcherEmail;
    public Integer articleId;
    public String createdAt;
    public boolean accepted;


}
