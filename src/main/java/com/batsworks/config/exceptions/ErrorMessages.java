package com.batsworks.config.exceptions;

import com.batsworks.config.enums.ErrorCodeMessage;
import org.jboss.logging.annotations.MessageBundle;

@MessageBundle(projectCode = "")
public interface ErrorMessages {

    String get(ErrorCodeMessage errorCodeMessage);

}
