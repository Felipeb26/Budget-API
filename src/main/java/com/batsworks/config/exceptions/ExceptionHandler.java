package com.batsworks.config.exceptions;

import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class ExceptionHandler implements ExceptionMapper<Exception> {

    private static final Logger log = LoggerFactory.getLogger(ExceptionHandler.class);

    @Context
    private UriInfo uriInfo;

    @Override
    public Response toResponse(Exception e) {
        log.error("|=| A error happen: ==> {}", e.getMessage());
        String url = uriInfo.getAbsolutePath().toString();
        DefaultExceptionHandler entity = new DefaultExceptionHandler(e.getMessage(), url);

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(entity).type(MediaType.APPLICATION_JSON).build();
    }
}
