package com.batsworks.config.exceptions;

import com.batsworks.config.enums.ErrorCodeMessage;
import org.jboss.resteasy.reactive.RestResponse;

public class UsuarioException extends BussinesApplicationException {


    public UsuarioException(RestResponse.Status status, ErrorCodeMessage message, Object[] args) {
        super(status, message, args);
    }

    public UsuarioException(RestResponse.Status status, ErrorCodeMessage message) {
        super(status, message);
    }
}
