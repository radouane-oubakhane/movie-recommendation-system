package com.radouaneoubakhane.userservice.controller;

import com.radouaneoubakhane.userservice.dto.movie.WatchedMovieResponse;

import java.util.List;

public interface WatchedMovieController {

    List<WatchedMovieResponse> getAllWatchedMovies();

    WatchedMovieResponse getMyWatchedMovie(Long id);

    void addWatchedMovie(Long id);

    void deleteWatchedMovie(Long id);
}
