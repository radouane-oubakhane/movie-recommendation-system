package com.radouaneoubakhane.userservice.util.DataGenerators;

import com.radouaneoubakhane.userservice.model.Profile;

import java.time.LocalDate;

public class ProfileDataGenerator {
    public static Profile generateProfile() {
        return Profile.builder()
                .id(1L)
                .firstName("test")
                .lastName("test")
                .profilePicture("test")
                .birthDate(LocalDate.now())
                .birthPlace("test")
                .bio("test")
                .preferences("test")
                .user(UserDataGenerator.generateUser())
                .build();
    }
}
