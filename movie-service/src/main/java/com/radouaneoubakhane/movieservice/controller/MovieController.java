package com.radouaneoubakhane.movieservice.controller;

import com.radouaneoubakhane.movieservice.dto.movie.ActorResponse;
import com.radouaneoubakhane.movieservice.dto.movie.MovieRequest;
import com.radouaneoubakhane.movieservice.dto.movie.MovieResponse;
import com.radouaneoubakhane.movieservice.enums.Genre;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MovieController {
    // CRUD operations ==========================================================
    // ==========================================================================
    Page<MovieResponse> getMoviesWithPaginationAndSorting(Integer pageNo, Integer pageSize, String[] sortBy);

    List<MovieResponse> getMovies();


    MovieResponse getMovie(Long id);


    MovieResponse createMovie(MovieRequest movieRequest);

    MovieResponse updateMovie(Long id, MovieRequest movieRequest);

    void deleteMovie(Long id);

    List<MovieResponse> searchMovies(String title);

    List<MovieResponse> searchMoviesByGenre(Genre genre);


    List<ActorResponse> getMovieActors(Long movieId);

    void addActorToMovie(Long movieId, Long actorId);

    void removeActorFromMovie(Long movieId, Long actorId);

    // Endpoints for the user-service ===========================================
    // ==========================================================================
    List<MovieResponse> getMoviesByIds(List<Long> id);
}
