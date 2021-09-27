package com.mycompany.freegame.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class GameNoExistsException extends Exception {

    public GameNoExistsException(String message) {
        super(message);
    }

    public GameNoExistsException() {

    }
}
