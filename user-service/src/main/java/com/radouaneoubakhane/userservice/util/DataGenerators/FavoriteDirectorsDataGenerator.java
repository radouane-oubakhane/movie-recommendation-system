package com.radouaneoubakhane.userservice.util.DataGenerators;

import com.radouaneoubakhane.userservice.model.FavoriteDirector;

public class FavoriteDirectorsDataGenerator {
    public static FavoriteDirector generateFavoriteDirectors() {
        return FavoriteDirector.builder()
                .directorId(1L)
                .user(UserDataGenerator.generateUser())
                .build();
    }
}
