package com.radouaneoubakhane.movieservice.exception.Director;

public class DirectorNotFoundException extends RuntimeException {
    public DirectorNotFoundException(String message) {
        super(message);
    }
}
