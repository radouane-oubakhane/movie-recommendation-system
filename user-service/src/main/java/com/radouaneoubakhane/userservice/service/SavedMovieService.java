package com.radouaneoubakhane.userservice.service;


import com.radouaneoubakhane.userservice.dto.movie.MovieResponse;
import com.radouaneoubakhane.userservice.entity.SavedMovie;
import com.radouaneoubakhane.userservice.entity.User;
import com.radouaneoubakhane.userservice.repository.SavedMovieRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class SavedMovieService {
    
    final private SavedMovieRepository savedMovieRepository;

    public List<MovieResponse> getMySavedMovies() {
        log.info("Getting All my saved movies");

        List<SavedMovie> savedMovies = savedMovieRepository.findAllByUserId(1L);

        // Call the movie-service to get the saved movies

        return savedMovies.stream()
                .map(this::mapSavedMoviesToMovieResponse)
                .toList();
    }

    private MovieResponse  mapSavedMoviesToMovieResponse(SavedMovie favoriteMovie) {
        return MovieResponse.builder()
                .id(favoriteMovie.getId())
                .movieId(favoriteMovie.getMovieId())
                .userId(favoriteMovie.getUserId())
                .build();
    }

    public MovieResponse getMySavedMovie(Long id) {
        log.info("Getting my saved movie with id {}", id);

        SavedMovie savedMovie = savedMovieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Saved movie not found"));

        if (savedMovie.getUserId() != 1L) {
            throw new RuntimeException("Saved movie not found");
        }

        // Call the movie-service to get the saved movie

        return mapSavedMoviesToMovieResponse(savedMovie);
    }

    public void addMySavedMovie(Long id) {
        log.info("Adding my saved movie with id {}", id);

        if (savedMovieRepository.existsByMovieIdAndUserId(id, 1L)) {
            throw new RuntimeException("Movie already saved");
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
                .orElseThrow(() -> new RuntimeException("Saved movie not found"));

        if (!savedMovieRepository.existsByMovieIdAndUserId(id, 1L)) {
            throw new RuntimeException("Movie not saved");
        }

        savedMovieRepository.delete(savedMovie);
    }
}
