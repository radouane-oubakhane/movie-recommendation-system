package com.radouaneoubakhane.userservice.util.DataGenerators;

import com.radouaneoubakhane.userservice.domain.WatchedMovie;

public class WatchedMoviesDataGenerator {
    public static WatchedMovie generateWatchedMovies() {
        return WatchedMovie.builder()
                .movieId(1L)
                .user(UserDataGenerator.generateUser())
                .build();
    }
}
