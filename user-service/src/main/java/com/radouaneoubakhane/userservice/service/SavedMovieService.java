package com.radouaneoubakhane.userservice.service;


import com.radouaneoubakhane.userservice.dto.movie.MovieResponse;
import com.radouaneoubakhane.userservice.dto.movie.SavedMovieResponse;
import com.radouaneoubakhane.userservice.entity.SavedMovie;
import com.radouaneoubakhane.userservice.entity.User;
import com.radouaneoubakhane.userservice.exception.movie.MovieNotFoundException;
import com.radouaneoubakhane.userservice.repository.SavedMovieRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class SavedMovieService {
    
    final private SavedMovieRepository savedMovieRepository;
    private final WebClient.Builder webClientBuilder;

    public List<SavedMovieResponse> getMySavedMovies() {
        log.info("Getting All my saved movies");

        List<SavedMovie> savedMovies = savedMovieRepository.findAllByUserId(1L);

        // Call the movie-service to get the saved movies
        // http://movie-service/api/v1/movie/ids?id=id
        List<MovieResponse> result = webClientBuilder.build()
                .get()
                .uri(
                        "lb://movie-service/api/v1/movies/ids",
                                uriBuilder -> uriBuilder
                                .queryParam("id", savedMovies.stream()
                                        .map(SavedMovie::getMovieId)
                                        .toList())
                                .build()
                        )
                .retrieve()
                .bodyToFlux(MovieResponse.class)
                .collectList()
                .block();

        return mapToSavedMovieResponse(savedMovies, result);
    }

    private List<SavedMovieResponse> mapToSavedMovieResponse(List<SavedMovie> savedMovies, List<MovieResponse> result) {
        return savedMovies.stream()
                .map(savedMovie -> {
                    MovieResponse movieResponse = result.stream()
                            .filter(movie -> movie.getId().equals(savedMovie.getMovieId()))
                            .findFirst()
                            .orElseThrow(() -> new MovieNotFoundException("Saved movie not found"));

                    return SavedMovieResponse.builder()
                            .id(savedMovie.getId())
                            .movie(movieResponse)
                            .build();
                })
                .toList();
    }


    public SavedMovieResponse getMySavedMovie(Long id) {
        log.info("Getting my saved movie with id {}", id);

        SavedMovie savedMovie = savedMovieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException("Saved movie not found"));

        if (savedMovie.getUser().getId() != 1L) {
            throw new RuntimeException("Saved movie not found");
        }

        // Call the movie-service to get the saved movie
        // http://movie-service/api/v1/movie/{id}
        MovieResponse movieResponse = webClientBuilder.build()
                .get()
                .uri(
                        "lb://movie-service/api/v1/movies/{id}",
                        uriBuilder -> uriBuilder
                                .build(savedMovie.getMovieId())
                )
                .retrieve()
                .bodyToMono(MovieResponse.class)
                .block();

        return SavedMovieResponse.builder()
                .id(savedMovie.getId())
                .movie(movieResponse)
                .build();
    }

    public void addMySavedMovie(Long id) {
        log.info("Adding my saved movie with id {}", id);

        if (savedMovieRepository.existsByMovieIdAndUserId(id, 1L)) {
            throw new RuntimeException("Movie already saved");
        }

        // Call the movie-service to validate if the movie exists
        // http://movie-service/api/v1/movie/{id}
        MovieResponse result = webClientBuilder.build()
                .get()
                .uri(
                        "lb://movie-service/api/v1/movies/{id}",
                        uriBuilder -> uriBuilder
                                .build(id)
                )
                .retrieve()
                .bodyToMono(MovieResponse.class)
                .block();

        if (result == null) {
            throw new MovieNotFoundException("Movie not found");
        }

        User user = User.builder()
                .id(1L)
                .build();

        SavedMovie savedMovie = SavedMovie.builder()
                .movieId(id)
                .user(user)
                .build();

        savedMovieRepository.save(savedMovie);
    }

    public void deleteMySavedMovie(Long id) {
        log.info("Deleting my saved movie with id {}", id);

        SavedMovie savedMovie = savedMovieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException("Saved movie not found"));

        if (savedMovie.getUser().getId() != 1L) {
            throw new RuntimeException("Saved movie not found");
        }

        savedMovieRepository.delete(savedMovie);
    }
}
