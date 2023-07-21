package com.radouaneoubakhane.userservice.service;


import com.radouaneoubakhane.userservice.dto.movie.MovieResponse;
import com.radouaneoubakhane.userservice.entity.User;
import com.radouaneoubakhane.userservice.entity.WatchlistMovie;
import com.radouaneoubakhane.userservice.repository.WatchlistMovieRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class WatchlistMovieService {
    private final WatchlistMovieRepository watchlistMovieRepository;


    public List<MovieResponse> getAllWatchlistMovies() {
        log.info("Getting all watchlist movies");

        List<WatchlistMovie> watchlistMovies = watchlistMovieRepository.findAllByUserId(1L);

        // Call the movie-service to get the watchlist movies

        return watchlistMovies.stream()
                .map(this::mapWatchlistMoviesToMovieResponse)
                .toList();
    }

    private MovieResponse mapWatchlistMoviesToMovieResponse(WatchlistMovie watchlistMovie) {
        return MovieResponse.builder()
                .id(watchlistMovie.getMovieId())
                .movieId(watchlistMovie.getMovieId())
                .userId(watchlistMovie.getUserId())
                .build();
    }


    public MovieResponse getMyWatchlistMovie(Long id) {
        log.info("Getting my watchlist movie with id {}", id);

        WatchlistMovie watchlistMovie = watchlistMovieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Watchlist movie not found"));

        if (watchlistMovie.getUserId() != 1L) {
            throw new RuntimeException("Watchlist movie not found");
        }

        // Call the movie-service to get the watchlist movie

        return mapWatchlistMoviesToMovieResponse(watchlistMovie);
    }

    public void addWatchlistMovie(Long id) {
        log.info("Adding my watchlist movie with id {}", id);

        if (watchlistMovieRepository.existsById(id)) {
            throw new RuntimeException("Watchlist movie already exists");
        }

        // Call the movie-service to validate if it exists

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
                .orElseThrow(() -> new RuntimeException("Watchlist movie not found"));

        if (watchlistMovie.getUserId() != 1L) {
            throw new RuntimeException("Watchlist movie not found");
        }

        watchlistMovieRepository.delete(watchlistMovie);
    }
}
