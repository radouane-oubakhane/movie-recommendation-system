package com.radouaneoubakhane.userservice.util.DataGenerators;

import com.radouaneoubakhane.userservice.entity.WatchedMovies;

public class WatchedMoviesDataGenerator {
    public static WatchedMovies generateWatchedMovies() {
        return WatchedMovies.builder()
                .movieId(1L)
                .user(UserDataGenerator.generateUser())
                .build();
    }
}
