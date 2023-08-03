package com.radouaneoubakhane.movieservice.service;

import com.radouaneoubakhane.movieservice.dto.movie.ActorResponse;
import com.radouaneoubakhane.movieservice.dto.movie.MovieRequest;
import com.radouaneoubakhane.movieservice.dto.movie.MovieResponse;
import com.radouaneoubakhane.movieservice.enums.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MovieService {

    Page<MovieResponse> getAllMovies(Pageable pageable);

    List<MovieResponse> getMovies();

    MovieResponse getMovie(Long id);

    MovieResponse createMovie(MovieRequest movieRequest);

    MovieResponse updateMovie(Long id, MovieRequest movieRequest);

    void deleteMovie(Long id);
    boolean existsById(Long id);

    List<ActorResponse> getMovieActors(Long movieId);

    void addActorToMovie(Long movieId, Long actorId);

    void removeActorFromMovie(Long movieId, Long actorId);

    List<MovieResponse> getMoviesByIds(List<Long> id);

    List<MovieResponse> searchMovies(String title);

    List<MovieResponse> searchMoviesByGenre(Genre genre);

}
