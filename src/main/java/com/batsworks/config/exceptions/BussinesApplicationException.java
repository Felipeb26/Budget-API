package com.batsworks.config.exceptions;

import com.batsworks.config.enums.ErrorCodeMessage;
import jakarta.ws.rs.WebApplicationException;
import org.jboss.resteasy.reactive.RestResponse;

public abstract class BussinesApplicationException extends WebApplicationException {

    private final ErrorCodeMessage errorCodeMessage;

    public ErrorCodeMessage getErrorCodeMessage() {
        return errorCodeMessage;
    }

    public BussinesApplicationException(RestResponse.Status status, ErrorCodeMessage errorCodeMessage, Object[] args) {
        super(status.getStatusCode());
        this.errorCodeMessage = errorCodeMessage;
    }

    public BussinesApplicationException(RestResponse.Status status, ErrorCodeMessage errorCodeMessage) {
        super(status.getStatusCode());
        this.errorCodeMessage = errorCodeMessage;
    }

}
