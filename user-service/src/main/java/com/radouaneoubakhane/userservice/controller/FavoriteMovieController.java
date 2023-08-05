package com.radouaneoubakhane.userservice.controller;

import com.radouaneoubakhane.userservice.dto.movie.FavoriteMovieResponse;

import java.util.List;

public interface FavoriteMovieController {

    List<FavoriteMovieResponse> getMyFavoriteMovies();

    FavoriteMovieResponse getMyFavoriteMovie(Long id);

    void addMyFavoriteMovie(Long id);

    void deleteMyFavoriteMovie(Long id);
}
