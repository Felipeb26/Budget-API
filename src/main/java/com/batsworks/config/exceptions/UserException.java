package com.batsworks.config.exceptions;

import com.batsworks.config.enums.ErrorCodeMessage;
import org.jboss.resteasy.reactive.RestResponse;

public class UserException extends BussinesApplicationException {

    public UserException(RestResponse.Status status, ErrorCodeMessage errorCodeMessage, Object... args) {
        super(status, errorCodeMessage, args);
    }

    public UserException(RestResponse.Status status, ErrorCodeMessage errorCodeMessage) {
        super(status, errorCodeMessage);
    }

    public UserException(int statusCode, ErrorCodeMessage errorCodeMessage, Object... args) {
        super(statusCode, errorCodeMessage, args);
    }

    public UserException(int statusCode, ErrorCodeMessage errorCodeMessage) {
        super(statusCode, errorCodeMessage);
    }
}
