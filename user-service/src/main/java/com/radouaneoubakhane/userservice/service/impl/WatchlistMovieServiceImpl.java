package com.radouaneoubakhane.userservice.service.impl;


import com.radouaneoubakhane.userservice.dto.movie.MovieResponse;
import com.radouaneoubakhane.userservice.dto.movie.WatchlistMovieResponse;
import com.radouaneoubakhane.userservice.domain.User;
import com.radouaneoubakhane.userservice.domain.WatchlistMovie;
import com.radouaneoubakhane.userservice.exception.movie.MovieNotFoundException;
import com.radouaneoubakhane.userservice.repository.WatchlistMovieRepository;
import com.radouaneoubakhane.userservice.service.WatchlistMovieService;
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
public class WatchlistMovieServiceImpl implements WatchlistMovieService {
    private final WatchlistMovieRepository watchlistMovieRepository;
    private final WebClient.Builder webClientBuilder;


    public List<WatchlistMovieResponse> getAllWatchlistMovies() {
        log.info("Getting all watchlist movies");

        List<WatchlistMovie> watchlistMovies = watchlistMovieRepository.findAllByUserId(1L);

        // Call the movie-service to get the watchlist movies
        // http://movie-service/api/v1/movie/ids?id=id
        List<MovieResponse> result = webClientBuilder.build()
                .get()
                .uri(
                        "lb://movie-service/api/v1/movies/ids",
                                uriBuilder -> uriBuilder
                                .queryParam("id", watchlistMovies.stream()
                                        .map(WatchlistMovie::getMovieId)
                                        .toList())
                                .build()
                        )
                .retrieve()
                .bodyToFlux(MovieResponse.class)
                .collectList()
                .block();


        return mapToMovieResponse(watchlistMovies, result);
    }

    private List<WatchlistMovieResponse> mapToMovieResponse(List<WatchlistMovie> watchlistMovies, List<MovieResponse> result) {
        return watchlistMovies.stream()
                .map(watchlistMovie -> {
                    MovieResponse movieResponse = result.stream()
                            .filter(movie -> movie.getId().equals(watchlistMovie.getMovieId()))
                            .findFirst()
                            .orElseThrow(() -> new MovieNotFoundException("Watchlist movie not found"));

                    return WatchlistMovieResponse.builder()
                            .id(watchlistMovie.getId())
                            .movie(movieResponse)
                            .build();
                })
                .toList();
    }


    public WatchlistMovieResponse getMyWatchlistMovie(Long id) {
        log.info("Getting my watchlist movie with id {}", id);

        WatchlistMovie watchlistMovie = watchlistMovieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException("Watchlist movie not found"));

        if (watchlistMovie.getUser().getId() != 1L) {
            throw new IllegalArgumentException("Watchlist movie not found");
        }

        // Call the movie-service to get the watchlist movie
        // http://movie-service/api/v1/movie/{id}
        MovieResponse movieResponse = webClientBuilder.build()
                .get()
                .uri(
                        "lb://movie-service/api/v1/movies/{id}",
                        uriBuilder -> uriBuilder
                                .build(watchlistMovie.getMovieId())
                )
                .retrieve()
                .bodyToMono(MovieResponse.class)
                .block();

        return WatchlistMovieResponse.builder()
                .id(watchlistMovie.getId())
                .movie(movieResponse)
                .build();
    }

    public void addWatchlistMovie(Long id) {
        log.info("Adding my watchlist movie with id {}", id);

        if (watchlistMovieRepository.existsById(id)) {
            throw new IllegalArgumentException("Watchlist movie already exists");
        }

        // Call the movie-service to validate if it exists
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

        WatchlistMovie watchlistMovie = WatchlistMovie.builder()
                .movieId(id)
                .user(user)
                .build();

        watchlistMovieRepository.save(watchlistMovie);
    }


    public void deleteWatchlistMovie(Long id) {
        log.info("Deleting my watchlist movie with id {}", id);

        WatchlistMovie watchlistMovie = watchlistMovieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException("Watchlist movie not found"));

        if (watchlistMovie.getUser().getId() != 1L) {
            throw new IllegalArgumentException("Watchlist movie not found");
        }

        watchlistMovieRepository.delete(watchlistMovie);
    }
}
