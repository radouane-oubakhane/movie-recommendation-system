package com.radouaneoubakhane.userservice.exception.profile;



public class ProfileNotFoundException extends RuntimeException {
    public ProfileNotFoundException(String message) {
        super(message);
    }
}
