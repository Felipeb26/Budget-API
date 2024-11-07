package com.batsworks.config.exceptions;

import com.batsworks.config.enums.ErrorCodeMessage;
import jakarta.ws.rs.WebApplicationException;
import org.jboss.resteasy.reactive.RestResponse;

public abstract class BussinesApplicationException extends WebApplicationException {

    private final ErrorCodeMessage errorCodeMessage;
    private final Object[] args;

    public ErrorCodeMessage getErrorCodeMessage() {
        return errorCodeMessage;
    }

    public Object[] getArgs() {
        return args;
    }

    public BussinesApplicationException(RestResponse.Status status, ErrorCodeMessage errorCodeMessage, Object... args) {
        super(status.getStatusCode());
        this.errorCodeMessage = errorCodeMessage;
        this.args = args;
    }

    public BussinesApplicationException(RestResponse.Status status, ErrorCodeMessage errorCodeMessage) {
        super(status.getStatusCode());
        this.errorCodeMessage = errorCodeMessage;
        this.args = new Object[]{};
    }

    public BussinesApplicationException(int statusCode, ErrorCodeMessage errorCodeMessage, Object... args) {
        super(statusCode);
        this.errorCodeMessage = errorCodeMessage;
        this.args = args;
    }

    public BussinesApplicationException(int statusCode, ErrorCodeMessage errorCodeMessage) {
        super(statusCode);
        this.errorCodeMessage = errorCodeMessage;
        this.args = new Object[]{};
    }

}
