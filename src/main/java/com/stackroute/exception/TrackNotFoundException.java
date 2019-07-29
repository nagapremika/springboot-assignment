package com.stackroute.exception;

public class TrackNotFoundException extends Exception{  //Exception for track not found
    public TrackNotFoundException (String message) {
        super(message);
        this.message = message;
    }

    private String message;

    public TrackNotFoundException() {
    }
}
