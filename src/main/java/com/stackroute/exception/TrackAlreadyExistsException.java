package com.stackroute.exception;

public class TrackAlreadyExistsException extends Exception {    //Exception for track already exists
    private String message;

    public TrackAlreadyExistsException(String message) {
        super(message);
        this.message = message;
    }

    public TrackAlreadyExistsException() {
    }
}
