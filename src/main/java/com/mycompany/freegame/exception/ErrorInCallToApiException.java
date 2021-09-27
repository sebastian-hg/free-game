package com.mycompany.freegame.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ErrorInCallToApiException extends Exception {

    public ErrorInCallToApiException(String message) {
        super(message);
    }

    public ErrorInCallToApiException() {

    }
}
