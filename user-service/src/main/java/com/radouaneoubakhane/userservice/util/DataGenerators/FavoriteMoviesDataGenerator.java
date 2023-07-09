package com.radouaneoubakhane.userservice.util.DataGenerators;

import com.radouaneoubakhane.userservice.entity.FavoriteMovies;

public class FavoriteMoviesDataGenerator {
    public static FavoriteMovies generateFavoriteMovies() {
        return FavoriteMovies.builder()
                .movieId(1L)
                .user(UserDataGenerator.generateUser())
                .build();
    }
}
