package com.batsworks.config.exceptions;

import jakarta.ws.rs.core.*;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class ConstraintKeyException implements ExceptionMapper<ConstraintViolationException> {

    private static final Logger log = LoggerFactory.getLogger(ConstraintKeyException.class);

    @Context
    private UriInfo uriInfo;

    @Context
    private Request request;

    @Override
    public Response toResponse(ConstraintViolationException e) {
        log.error("|=| A error happen: ==> {}", e.getMessage());
        String url = uriInfo.getAbsolutePath().toString();
        DefaultExceptionHandler entity = new DefaultExceptionHandler(e.getMessage(), url, request.getMethod());

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(entity).type(MediaType.APPLICATION_JSON).build();
    }
}