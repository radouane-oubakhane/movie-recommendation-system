package com.radouaneoubakhane.userservice.util.DataGenerators;

import com.radouaneoubakhane.userservice.entity.Watchlist;

public class WatchlistDataGenerator {
    public static Watchlist generateWatchlist() {
        return Watchlist.builder()
                .movieId(1L)
                .user(UserDataGenerator.generateUser())
                .build();
    }
}
