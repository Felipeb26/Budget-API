package com.batsworks.config.exceptions;

import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ExceptionHandler implements ExceptionMapper<IllegalArgumentException> {

    @Context
    private UriInfo uriInfo;

    @Override
    public Response toResponse(IllegalArgumentException e) {
        String url = uriInfo.getAbsolutePath().toString();
        DefaultExceptionHandler entity = new DefaultExceptionHandler(e.getMessage(), url);

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(entity).type(MediaType.APPLICATION_JSON).build();
    }
}
