package com.radouaneoubakhane.userservice.service;

import com.radouaneoubakhane.userservice.dto.movie.FavoriteMovieResponse;

import java.util.List;

public interface FavoriteMovieService {
    List<FavoriteMovieResponse> getMyFavoriteMovies();

    FavoriteMovieResponse getMyFavoriteMovie(Long id);

    void addMyFavoriteMovie(Long id);

    void deleteMyFavoriteMovie(Long id);
}
