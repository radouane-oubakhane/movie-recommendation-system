package com.radouaneoubakhane.userservice.service;

import com.radouaneoubakhane.userservice.dto.movie.SavedMovieResponse;

import java.util.List;

public interface SavedMovieService {
    List<SavedMovieResponse> getMySavedMovies();

    SavedMovieResponse getMySavedMovie(Long id);

    void addMySavedMovie(Long id);

    void deleteMySavedMovie(Long id);
}
