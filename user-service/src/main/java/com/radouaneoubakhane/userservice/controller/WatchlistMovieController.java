package com.radouaneoubakhane.userservice.controller;

import com.radouaneoubakhane.userservice.dto.movie.WatchlistMovieResponse;

import java.util.List;

public interface WatchlistMovieController {
    List<WatchlistMovieResponse> getAllWatchlistMovies();


    WatchlistMovieResponse getMyWatchlistMovie(Long id);


    void addWatchlistMovie(Long id);


    void deleteWatchlistMovie(Long id);
}
