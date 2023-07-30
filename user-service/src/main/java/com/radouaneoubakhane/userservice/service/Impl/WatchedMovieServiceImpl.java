package com.radouaneoubakhane.userservice.service.Impl;

import com.radouaneoubakhane.userservice.dto.movie.MovieResponse;
import com.radouaneoubakhane.userservice.dto.movie.WatchedMovieResponse;
import com.radouaneoubakhane.userservice.entity.User;
import com.radouaneoubakhane.userservice.entity.WatchedMovie;
import com.radouaneoubakhane.userservice.exception.movie.MovieNotFoundException;
import com.radouaneoubakhane.userservice.repository.WatchedMovieRepository;
import com.radouaneoubakhane.userservice.service.WatchedMovieService;
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
public class WatchedMovieServiceImpl implements WatchedMovieService {
    private final WatchedMovieRepository watchedMovieRepository;
    private final WebClient.Builder webClientBuilder;

    public List<WatchedMovieResponse> getAllWatchedMovies() {
        log.info("Getting all watched movies");

        List<WatchedMovie> watchedMovies = watchedMovieRepository.findAllByUserId(1L);

        // Call the movie-service to get the watched movies
        // http://movie-service/api/v1/movie/ids?id=id
        List<MovieResponse> result = webClientBuilder.build()
                .get()
                .uri(
                        "lb://movie-service/api/v1/movies/ids",
                                uriBuilder -> uriBuilder
                                .queryParam("id", watchedMovies.stream()
                                        .map(WatchedMovie::getMovieId)
                                        .toList())
                                .build()
                        )
                .retrieve()
                .bodyToFlux(MovieResponse.class)
                .collectList()
                .block();


        return mapToMovieResponse(watchedMovies, result);
    }

    private List<WatchedMovieResponse> mapToMovieResponse(List<WatchedMovie> watchedMovies, List<MovieResponse> result) {
        return watchedMovies.stream()
                .map(watchedMovie -> {
                    MovieResponse movieResponse = result.stream()
                            .filter(movie -> movie.getId().equals(watchedMovie.getMovieId()))
                            .findFirst()
                            .orElseThrow(() -> new MovieNotFoundException("Watched movie not found"));

                    return WatchedMovieResponse.builder()
                            .id(watchedMovie.getId())
                            .movie(movieResponse)
                            .build();
                })
                .toList();
    }


    public WatchedMovieResponse getMyWatchedMovie(Long id) {
        log.info("Getting my watched movie with id {}", id);

            WatchedMovie watchedMovie = watchedMovieRepository.findById(id)
                    .orElseThrow(() -> new MovieNotFoundException("Watched movie not found"));

            if (watchedMovie.getUser().getId() != 1L) {
                throw new RuntimeException("Watched movie not found");
            }

        // Call the movie-service to get the watched movie
        // http://movie-service/api/v1/movie/{id}
        MovieResponse movieResponse = webClientBuilder.build()
                .get()
                .uri(
                        "lb://movie-service/api/v1/movies/{id}",
                        uriBuilder -> uriBuilder
                                .build(watchedMovie.getMovieId())
                )
                .retrieve()
                .bodyToMono(MovieResponse.class)
                .block();

        return WatchedMovieResponse.builder()
                .id(watchedMovie.getId())
                .movie(movieResponse)
                .build();
    }

    public void addWatchedMovie(Long id) {
        log.info("Adding my watched movie with id {}", id);

        if (watchedMovieRepository.existsByMovieIdAndUserId(id, 1L)) {
            throw new RuntimeException("Watched movie already exists");
        }

        // Call the movie-service to validate the movie if it exists
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

        WatchedMovie watchedMovie = WatchedMovie.builder()
                .movieId(id)
                .user(user)
                .build();

        watchedMovieRepository.save(watchedMovie);
    }

    public void deleteWatchedMovie(Long id) {
        log.info("Deleting my watched movie with id {}", id);

        WatchedMovie watchedMovie = watchedMovieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException("Watched movie not found"));

        if (watchedMovie.getUser().getId() != 1L) {
            throw new RuntimeException("Watched movie not found");
        }

        watchedMovieRepository.delete(watchedMovie);
    }
}
