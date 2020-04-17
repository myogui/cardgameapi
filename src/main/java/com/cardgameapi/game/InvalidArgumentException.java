package com.cardgameapi.game;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidArgumentException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    
    public InvalidArgumentException(String argumentName){
        super("The following argument received has an invalid value: " + argumentName);
    }
}