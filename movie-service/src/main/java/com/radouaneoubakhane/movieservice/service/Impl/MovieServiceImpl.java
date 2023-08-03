package com.radouaneoubakhane.movieservice.service.Impl;


import com.radouaneoubakhane.movieservice.dto.movie.*;
import com.radouaneoubakhane.movieservice.entity.Actor;
import com.radouaneoubakhane.movieservice.entity.Director;
import com.radouaneoubakhane.movieservice.entity.Movie;
import com.radouaneoubakhane.movieservice.entity.Rating;
import com.radouaneoubakhane.movieservice.enums.Genre;
import com.radouaneoubakhane.movieservice.event.NewMovieAddedEvent;
import com.radouaneoubakhane.movieservice.exception.Actor.ActorNotFoundException;
import com.radouaneoubakhane.movieservice.exception.Movie.MovieNotFoundException;
import com.radouaneoubakhane.movieservice.mapper.MovieMapper;
import com.radouaneoubakhane.movieservice.repository.MovieRepository;
import com.radouaneoubakhane.movieservice.service.ActorService;
import com.radouaneoubakhane.movieservice.service.MovieService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@Transactional
@Slf4j
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final ActorService actorService;
    private final KafkaTemplate<String, NewMovieAddedEvent> kafkaTemplate;


    public Page<MovieResponse> getAllMovies(Pageable pageable) {
        log.info("Fetching all movies");

        Page<Movie> movies = movieRepository.findAll(pageable);

        return movies.map(this::mapMovieToMovieResponse);
    }

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

        Movie movie = movieRepository.findById(id).orElseThrow(
                () -> new MovieNotFoundException("Movie not found")
        );

        return mapMovieToMovieResponse(movieRepository.save(movie));
    }


    public MovieResponse createMovie(MovieRequest movieRequest) {
        log.info("Creating movie: {}", movieRequest);

        Movie movie = mapMovieRequestToMovie(movieRequest);

        kafkaTemplate.send("new-movie-added", NewMovieAddedEvent.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .description(movie.getDescription())
                .poster(movie.getPoster())
                .releaseDate(movie.getReleaseDate())
                .duration(movie.getDuration())
                .language(movie.getLanguage())
                .build());

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

        ActorResponse actorResponse = MovieMapper.map(
                actorService.getActor(actorId)
        );

        if (actorResponse == null) {
            throw new ActorNotFoundException("Actor not found");
        }

        if (movie.getActors().stream().map(Actor::getId).toList().contains(actorId)) {
            throw new IllegalArgumentException("Actor already exists");
        }

        movie.getActors().add(
                Actor.builder()
                        .id(actorResponse.getId())
                        .firstName(actorResponse.getFirstName())
                        .lastName(actorResponse.getLastName())
                        .picture(actorResponse.getPicture())
                        .build()
        );
    }

    public void removeActorFromMovie(Long movieId, Long actorId) {
        log.info("Removing actor with id: {} from movie with id: {}", actorId, movieId);

        Movie movie = movieRepository.findById(movieId).orElseThrow(
                () -> new MovieNotFoundException("Movie not found")
        );

        ActorResponse actorResponse = MovieMapper.map(
                actorService.getActor(actorId)
        );

        if (actorResponse == null) {
            throw new ActorNotFoundException("Actor not found");
        }


        Actor actorToRemove = movie.getActors().stream()
                .filter(actor -> actor.getId().equals(actorId))
                .findFirst()
                .orElseThrow(
                        () -> new IllegalArgumentException("Actor not found")
                );


        movie.getActors().remove(actorToRemove);
    }

    public List<MovieResponse> getMoviesByIds(List<Long> id) {
        log.info("Fetching movies with ids: {}", id);

        List<Movie> movies = movieRepository.findAllById(id);

        return movies.stream().map(this::mapMovieToMovieResponse).toList();
    }

    public boolean existsById(Long id) {
        return movieRepository.existsById(id);
    }
}
