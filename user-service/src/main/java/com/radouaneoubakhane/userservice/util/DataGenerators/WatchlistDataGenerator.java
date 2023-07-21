package com.radouaneoubakhane.userservice.util.DataGenerators;

import com.radouaneoubakhane.userservice.entity.WatchlistMovie;

public class WatchlistDataGenerator {
    public static WatchlistMovie generateWatchlist() {
        return WatchlistMovie.builder()
                .movieId(1L)
                .user(UserDataGenerator.generateUser())
                .build();
    }
}
