package com.radouaneoubakhane.movieservice.service;


import com.radouaneoubakhane.movieservice.dto.Movie.*;
import com.radouaneoubakhane.movieservice.entity.Actor;
import com.radouaneoubakhane.movieservice.entity.Director;
import com.radouaneoubakhane.movieservice.entity.Movie;
import com.radouaneoubakhane.movieservice.entity.Rating;
import com.radouaneoubakhane.movieservice.enums.Genre;
import com.radouaneoubakhane.movieservice.exception.Actor.ActorNotFoundException;
import com.radouaneoubakhane.movieservice.exception.Movie.MovieNotFoundException;
import com.radouaneoubakhane.movieservice.repository.ActorRepository;
import com.radouaneoubakhane.movieservice.repository.MovieRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MovieService {

    private final MovieRepository movieRepository;
    private final ActorRepository actorRepository;

    public List<MovieResponse> getMovies() {
        log.info("Fetching all movies");

        List<Movie> movies = movieRepository.findAll();

        return movies.stream().map(this::mapMovieToMovieResponse).toList();
    }

    private MovieResponse mapMovieToMovieResponse(Movie movie) {
        return MovieResponse.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .description(movie.getDescription())
                .poster(movie.getPoster())
                .releaseDate(movie.getReleaseDate())
                .duration(movie.getDuration())
                .language(movie.getLanguage())
                .country(movie.getCountry())
                .averageRating(movie.getAverageRating())
                .director(mapDirectorToDirectorResponse(movie.getDirector()))
                .actors(mapActorsToActorResponses(movie.getActors()))
                .ratings(mapRatingsToRatingResponses(movie.getRatings()))
                .genre(movie.getGenre())
                .build();
    }

    private DirectorResponse mapDirectorToDirectorResponse(Director director) {
        if (director == null) {
            return null;
        }
        return DirectorResponse.builder()
                .id(director.getId())
                .firstName(director.getFirstName())
                .lastName(director.getLastName())
                .picture(director.getPicture())
                .build();
    }

    private List<ActorResponse> mapActorsToActorResponses(List<Actor> actors) {
        if (actors == null) {
            return null;
        }
        return actors.stream().map(actor -> ActorResponse.builder()
                .id(actor.getId())
                .firstName(actor.getFirstName())
                .lastName(actor.getLastName())
                .picture(actor.getPicture())
                .build()).toList();
    }

    private List<RatingResponse> mapRatingsToRatingResponses(List<Rating> ratings) {
        if (ratings == null) {
            return null;
        }
        return ratings.stream().map(rating -> RatingResponse.builder()
                .id(rating.getId())
                .userId(rating.getUserId())
                .rating(rating.getRating())
                .review(rating.getReview())
                .timestamp(rating.getTimestamp())
                .build()).toList();
    }

    public MovieResponse getMovie(Long id) {
        log.info("Fetching movie with id: {}", id);

        Movie movie = movieRepository.findById(id).orElseThrow(() -> new MovieNotFoundException("Movie not found"));

        return mapMovieToMovieResponse(movieRepository.save(movie));
    }


    public MovieResponse createMovie(MovieRequest movieRequest) {
        log.info("Creating movie: {}", movieRequest);

        Movie movie = mapMovieRequestToMovie(movieRequest);

        return mapMovieToMovieResponse(movieRepository.save(movie));
    }

    private Movie mapMovieRequestToMovie(MovieRequest movieRequest) {
        return Movie.builder()
                .title(movieRequest.getTitle())
                .description(movieRequest.getDescription())
                .poster(movieRequest.getPoster())
                .releaseDate(movieRequest.getReleaseDate())
                .duration(movieRequest.getDuration())
                .language(movieRequest.getLanguage())
                .country(movieRequest.getCountry())
                .genre(movieRequest.getGenre())
                .build();
    }

    public MovieResponse updateMovie(Long id, MovieRequest movieRequest) {
        log.info("Updating movie with id: {}", id);

        Movie movieToUpdate = movieRepository.findById(id).orElseThrow(
                () -> new MovieNotFoundException("Movie not found")
        );

        movieToUpdate.setTitle(movieRequest.getTitle());
        movieToUpdate.setDescription(movieRequest.getDescription());
        movieToUpdate.setPoster(movieRequest.getPoster());
        movieToUpdate.setReleaseDate(movieRequest.getReleaseDate());
        movieToUpdate.setDuration(movieRequest.getDuration());
        movieToUpdate.setLanguage(movieRequest.getLanguage());
        movieToUpdate.setCountry(movieRequest.getCountry());
        movieToUpdate.setGenre(movieRequest.getGenre());

        return mapMovieToMovieResponse(movieToUpdate);
    }


    public void deleteMovie(Long id) {
        log.info("Deleting movie with id: {}", id);

        Movie movieToDelete = movieRepository.findById(id).orElseThrow(
                () -> new MovieNotFoundException("Movie not found")
        );

        movieRepository.delete(movieToDelete);
    }

    public List<MovieResponse> searchMovies(String title) {
        log.info("Searching movies with title: {}", title);

        List<Movie> movies = movieRepository.findByTitleContainingIgnoreCase(title);

        return movies.stream().map(this::mapMovieToMovieResponse).toList();
    }

    public List<MovieResponse> searchMoviesByGenre(Genre genre) {
        log.info("Searching movies with genre: {}", genre);

        List<Movie> movies = movieRepository.findByGenre(genre);

        return movies.stream().map(this::mapMovieToMovieResponse).toList();
    }


    public List<ActorResponse> getMovieActors(Long movieId) {
        log.info("Fetching actors for movie with id: {}", movieId);

        Movie movie = movieRepository.findById(movieId).orElseThrow(
                () -> new MovieNotFoundException("Movie not found")
        );

        return mapActorsToActorResponses(movie.getActors());
    }

    public void addActorToMovie(Long movieId, Long actorId) {
        log.info("Adding actor with id: {} to movie with id: {}", actorId, movieId);

        Movie movie = movieRepository.findById(movieId).orElseThrow(
                () -> new MovieNotFoundException("Movie not found")
        );

        Actor actor = actorRepository.findById(actorId).orElseThrow(
                () -> new ActorNotFoundException("Actor not found")
        );

        if (movie.getActors().contains(actor)) {
            throw new IllegalArgumentException("Movie already exists");
        }

        actor.getMovies().add(movie);
    }

    public void removeActorFromMovie(Long movieId, Long actorId) {
        log.info("Removing actor with id: {} from movie with id: {}", actorId, movieId);

        Movie movie = movieRepository.findById(movieId).orElseThrow(
                () -> new MovieNotFoundException("Movie not found")
        );

        Actor actor = actorRepository.findById(actorId).orElseThrow(
                () -> new ActorNotFoundException("Actor not found")
        );

        if (!movie.getActors().contains(actor)) {
            throw new ActorNotFoundException("Actor not found");
        }

        actor.getMovies().remove(movie);
    }

    public List<MovieResponse> getMoviesByIds(List<Long> id) {
        log.info("Fetching movies with ids: {}", id);

        List<Movie> movies = movieRepository.findAllById(id);

        return movies.stream().map(this::mapMovieToMovieResponse).toList();
    }
}
