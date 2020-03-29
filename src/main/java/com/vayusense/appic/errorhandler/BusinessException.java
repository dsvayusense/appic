package com.vayusense.appic.errorhandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "server /business exception")
public class BusinessException extends RuntimeException {
    //Add header field
    public BusinessException() {
        super();
    }

    public BusinessException(final String message) {
        super(message);
    }

}
