package com.radouaneoubakhane.userservice.util.DataGenerators;

import com.radouaneoubakhane.userservice.entity.User;

public class UserDataGenerator {
    public static User generateUser() {
        return User.builder()
                .id(1L)
                .username("test")
                .password("test")
                .email("test@test.com")
                .build();
    }
}
