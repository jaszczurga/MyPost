package org.example.mypost.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;



@ResponseStatus (value = HttpStatus.METHOD_NOT_ALLOWED)
public class FriendShipAlreadyExistsException extends RuntimeException{
    public FriendShipAlreadyExistsException(String resourceName) {
        super(String.format("It is not allowe to create friendship because it already exists "+resourceName));
    }
}