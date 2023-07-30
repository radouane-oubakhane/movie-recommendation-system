package com.radouaneoubakhane.userservice.service;

import com.radouaneoubakhane.userservice.dto.movie.WatchedMovieResponse;

import java.util.List;

public interface WatchedMovieService {
    List<WatchedMovieResponse> getAllWatchedMovies();

    WatchedMovieResponse getMyWatchedMovie(Long id);

    void addWatchedMovie(Long id);

    void deleteWatchedMovie(Long id);
}
