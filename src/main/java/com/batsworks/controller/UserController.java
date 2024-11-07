package com.batsworks.controller;

import com.batsworks.domain.dto.PageDTO;
import com.batsworks.domain.entity.UserEntity;
import com.batsworks.domain.service.UserService;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.jboss.logging.annotations.Param;

import java.util.UUID;

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
    public Uni<PageDTO<UserEntity>> findAll(@QueryParam("id") String id,
                                            @QueryParam("email") String email,
                                            @QueryParam("name") String name,
                                            @QueryParam("page") @DefaultValue("0") int page,
                                            @QueryParam("size") @DefaultValue("10") int size) {
        return service.findAll(id, name, email, page, size);
    }

    @GET
    @Path("/{id}")
    public Uni<UserEntity> findById(@Param UUID id) {
        return service.findById(id);
    }

    @POST
    public Uni<UserEntity> save(UserEntity user) {
        return service.save(user);
    }

    @PUT
    @Path("/{id}")
    public Uni<Void> updateEntity(@Param UUID id , @RequestBody UserEntity user){
        return service.updateUser(id, user);
    }


    @Path("/{id}")
    @DELETE
    public Uni<Void> deleteUser(@Param UUID id){
        return service.deleteById(id);
    }
}
