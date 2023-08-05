package com.radouaneoubakhane.userservice.controller;

import com.radouaneoubakhane.userservice.dto.movie.SavedMovieResponse;

import java.util.List;

public interface SavedMovieController {

    List<SavedMovieResponse> getMySavedMovies();


    SavedMovieResponse getMySavedMovie(Long id);


    void addMySavedMovie(Long id);


    void deleteMySavedMovie(Long id);
}
