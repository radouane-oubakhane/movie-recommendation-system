package com.radouaneoubakhane.movieservice.exception.Rating;

public class RatingNotFoundException extends RuntimeException{
    public RatingNotFoundException(String message) {
        super(message);
    }
}
