package com.radouaneoubakhane.userservice.exception.movie;

public class MovieNotFoundException extends RuntimeException {
    public MovieNotFoundException(String message) {
        super(message);
    }
}
