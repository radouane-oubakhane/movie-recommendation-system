package com.radouaneoubakhane.userservice.util.DataGenerators;

import com.radouaneoubakhane.userservice.model.FavoriteActor;

public class FavoriteActorsDataGenerator {
    public static FavoriteActor getInstance() {
        return FavoriteActor.builder()
                .id(1L)
                .user(UserDataGenerator.generateUser())
                .actorId(1L)
                .build();
    }
}
