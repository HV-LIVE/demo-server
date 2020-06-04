package org.hvlive.server.demoserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends ErrorException {
    public BadRequestException(int code) {
        super(code);
    }

    public BadRequestException(int code, String message) {
        super(code, message);
    }
}
