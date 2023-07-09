package com.radouaneoubakhane.userservice.util.DataGenerators;

import com.radouaneoubakhane.userservice.entity.FavoriteActors;

public class FavoriteActorsDataGenerator {
    public static FavoriteActors getInstance() {
        return FavoriteActors.builder()
                .id(1L)
                .user(UserDataGenerator.generateUser())
                .actorId(1L)
                .build();
    }
}
