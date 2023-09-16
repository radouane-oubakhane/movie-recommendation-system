package com.radouaneoubakhane.userservice.util.DataGenerators;

import com.radouaneoubakhane.userservice.model.FavoriteMovie;

public class FavoriteMoviesDataGenerator {
    public static FavoriteMovie generateFavoriteMovies() {
        return FavoriteMovie.builder()
                .movieId(1L)
                .user(UserDataGenerator.generateUser())
                .build();
    }
}
