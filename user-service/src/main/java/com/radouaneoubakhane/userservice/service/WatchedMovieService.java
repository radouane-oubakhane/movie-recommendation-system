package com.radouaneoubakhane.userservice.service;

import com.radouaneoubakhane.userservice.dto.movie.MovieResponse;
import com.radouaneoubakhane.userservice.entity.User;
import com.radouaneoubakhane.userservice.entity.WatchedMovie;
import com.radouaneoubakhane.userservice.repository.WatchedMovieRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class WatchedMovieService {
    private final WatchedMovieRepository watchedMovieRepository;

    public List<MovieResponse> getAllWatchedMovies() {
        log.info("Getting all watched movies");

        List<WatchedMovie> watchedMovies = watchedMovieRepository.findAllByUserId(1L);

        // Call the movie-service to get the watched movies

        return watchedMovies.stream()
                .map(this::mapWatchedMovieToMovieResponse)
                .toList();
    }

    private MovieResponse mapWatchedMovieToMovieResponse(WatchedMovie watchedMovie) {
        return MovieResponse.builder()
                .id(watchedMovie.getMovieId())
                .movieId(watchedMovie.getMovieId())
                .userId(watchedMovie.getUserId())
                .build();
    }

    public MovieResponse getMyWatchedMovie(Long id) {
        log.info("Getting my watched movie with id {}", id);

            WatchedMovie watchedMovie = watchedMovieRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Watched movie not found"));

            if (watchedMovie.getUserId() != 1L) {
                throw new RuntimeException("Watched movie not found");
            }

            // Call the movie-service to get the watched movie

            return mapWatchedMovieToMovieResponse(watchedMovie);
    }

    public void addWatchedMovie(Long id) {
        log.info("Adding my watched movie with id {}", id);

        if (watchedMovieRepository.existsByMovieIdAndUserId(id, 1L)) {
            throw new RuntimeException("Watched movie already exists");
        }

        // Call the movie-service to validate the movie if it exists

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
                .orElseThrow(() -> new RuntimeException("Watched movie not found"));

        if (watchedMovie.getUserId() != 1L) {
            throw new RuntimeException("Watched movie not found");
        }

        watchedMovieRepository.delete(watchedMovie);
    }
}
