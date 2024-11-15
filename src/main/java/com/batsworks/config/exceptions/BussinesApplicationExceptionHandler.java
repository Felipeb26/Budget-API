package com.batsworks.config.exceptions;

import com.batsworks.config.messages.RetriveMessage;
import jakarta.ws.rs.core.*;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class BussinesApplicationExceptionHandler implements ExceptionMapper<BussinesApplicationException> {

    private static final Logger log = LoggerFactory.getLogger(BussinesApplicationExceptionHandler.class);

    @Context
    private UriInfo uriInfo;
    @Context
    private Request request;

    @Override
    public Response toResponse(BussinesApplicationException e) {
        log.error("|=| A error happen: ==> {}", e.getMessage());
        String url = uriInfo.getAbsolutePath().toString();
        var message = RetriveMessage.get(e.getErrorCodeMessage(), e.getArgs());
        DefaultExceptionHandler entity = new DefaultExceptionHandler(e.getClass().getCanonicalName(), message, url, request.getMethod());

        return Response.status(e.getResponse().getStatus())
                .entity(entity).type(MediaType.APPLICATION_JSON).build();
    }

}
