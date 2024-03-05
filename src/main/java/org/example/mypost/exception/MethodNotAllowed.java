package org.example.mypost.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;



@ResponseStatus (value = HttpStatus.METHOD_NOT_ALLOWED)
public class MethodNotAllowed extends RuntimeException{
    public MethodNotAllowed(String resourceName) {
        super(String.format(resourceName));
    }
}

