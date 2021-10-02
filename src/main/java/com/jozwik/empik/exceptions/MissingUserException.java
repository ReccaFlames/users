package com.jozwik.empik.exceptions;

public class MissingUserException extends RuntimeException {

    public MissingUserException(String message) {
        super(message);
    }
}
