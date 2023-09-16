package com.radouaneoubakhane.userservice.util.DataGenerators;

import com.radouaneoubakhane.userservice.model.SavedMovie;

public class SavedMoviesDataGenerator {
    public static SavedMovie generateSavedMovies() {
        return SavedMovie.builder()
                .movieId(1L)
                .user(UserDataGenerator.generateUser())
                .build();
    }
}
