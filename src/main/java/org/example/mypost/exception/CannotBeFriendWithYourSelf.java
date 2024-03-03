package org.example.mypost.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;



@ResponseStatus (value = HttpStatus.METHOD_NOT_ALLOWED)
public class CannotBeFriendWithYourSelf extends RuntimeException{
    public CannotBeFriendWithYourSelf(String resourceName) {
        super(String.format("It is not allowed to create relationship with yourself "+resourceName));
    }
}

