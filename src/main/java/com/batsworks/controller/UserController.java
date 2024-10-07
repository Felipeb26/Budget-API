package com.batsworks.controller;

import com.batsworks.domain.entity.UserEntity;
import com.batsworks.domain.service.UserService;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.jboss.logging.annotations.Param;

@Path("/v1/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

    private final UserService service;

    @Inject
    public UserController(UserService userService) {
        this.service = userService;
    }

    @GET
    @Path("/{id}")
    public Uni<UserEntity> findByid(@Param String id) {
        return service.findById(id);
    }

    @POST
    public Uni<UserEntity> save(UserEntity userEntity) {
        return service.save(userEntity);
    }
}
