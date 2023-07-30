package com.radouaneoubakhane.movieservice.service;

import com.radouaneoubakhane.movieservice.dto.Movie.ActorResponse;
import com.radouaneoubakhane.movieservice.dto.Movie.MovieRequest;
import com.radouaneoubakhane.movieservice.dto.Movie.MovieResponse;
import com.radouaneoubakhane.movieservice.enums.Genre;

import java.util.List;

public interface MovieService {
    List<MovieResponse> getMovies();

    MovieResponse getMovie(Long id);

    MovieResponse createMovie(MovieRequest movieRequest);

    MovieResponse updateMovie(Long id, MovieRequest movieRequest);

    void deleteMovie(Long id);

    List<MovieResponse> searchMoviesByGenre(Genre genre);

    List<ActorResponse> getMovieActors(Long movieId);

    void addActorToMovie(Long movieId, Long actorId);

    void removeActorFromMovie(Long movieId, Long actorId);

    List<MovieResponse> getMoviesByIds(List<Long> id);

    List<MovieResponse> searchMovies(String title);
}
