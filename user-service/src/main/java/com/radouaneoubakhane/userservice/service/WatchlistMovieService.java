package com.radouaneoubakhane.userservice.service;

import com.radouaneoubakhane.userservice.dto.movie.WatchlistMovieResponse;

import java.util.List;

public interface WatchlistMovieService {
    List<WatchlistMovieResponse> getAllWatchlistMovies();

    WatchlistMovieResponse getMyWatchlistMovie(Long id);

    void addWatchlistMovie(Long id);

    void deleteWatchlistMovie(Long id);
}

