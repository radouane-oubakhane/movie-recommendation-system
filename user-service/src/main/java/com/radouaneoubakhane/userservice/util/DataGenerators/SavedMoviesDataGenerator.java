package com.radouaneoubakhane.userservice.util.DataGenerators;

import com.radouaneoubakhane.userservice.entity.SavedMovies;

public class SavedMoviesDataGenerator {
    public static SavedMovies generateSavedMovies() {
        return SavedMovies.builder()
                .movieId(1L)
                .user(UserDataGenerator.generateUser())
                .build();
    }
}
