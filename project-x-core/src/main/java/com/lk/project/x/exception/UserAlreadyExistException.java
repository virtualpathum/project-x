package com.lk.project.x.exception;

public class UserAlreadyExistException extends RuntimeException{
    public UserAlreadyExistException(final String message) {
        super(message);
    }
}
