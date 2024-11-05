package com.batsworks.config.exceptions;

import jakarta.inject.Inject;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class BussinesApplicationExceptionHandler implements ExceptionMapper<BussinesApplicationException> {

    private static final Logger log = LoggerFactory.getLogger(BussinesApplicationExceptionHandler.class);

    @Context
    private UriInfo uriInfo;

    @Inject
    ErrorMessages errorMessages;

    @Override
    public Response toResponse(BussinesApplicationException e) {
        log.error("|=| A error happen: ==> {}", e.getMessage());
        String url = uriInfo.getAbsolutePath().toString();
        var message = errorMessages.get(e.getErrorCodeMessage());
        DefaultExceptionHandler entity = new DefaultExceptionHandler(message, url);

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(entity).type(MediaType.APPLICATION_JSON).build();
    }

}
