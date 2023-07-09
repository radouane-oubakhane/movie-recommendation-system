package com.radouaneoubakhane.userservice.util.DataGenerators;

import com.radouaneoubakhane.userservice.entity.FavoriteDirectors;

public class FavoriteDirectorsDataGenerator {
    public static FavoriteDirectors generateFavoriteDirectors() {
        return FavoriteDirectors.builder()
                .directorId(1L)
                .user(UserDataGenerator.generateUser())
                .build();
    }
}
