package com.radouaneoubakhane.movieservice.exception.Actor;

public class ActorNotFoundException extends RuntimeException{
    public ActorNotFoundException(String message) {
        super(message);
    }
}
