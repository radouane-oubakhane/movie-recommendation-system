package com.radouaneoubakhane.userservice.service;


import com.radouaneoubakhane.userservice.dto.movie.MovieResponse;
import com.radouaneoubakhane.userservice.entity.FavoriteMovie;
import com.radouaneoubakhane.userservice.entity.User;
import com.radouaneoubakhane.userservice.exception.movie.MovieNotFoundException;
import com.radouaneoubakhane.userservice.repository.FavoriteMovieRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class FavoriteMovieService {

    final private FavoriteMovieRepository favoriteMovieRepository;

    public List<MovieResponse> getMyFavoriteMovies() {
        log.info("getMyFavoriteMovies");

        List<FavoriteMovie> favoriteMovies = favoriteMovieRepository.findAllByUserId(1L);

        // Call the movie-service to get the favorite movies

        return favoriteMovies.stream()
                        .map(this::mapFavoriteMoviesToMovieResponse)
                        .toList();

    }

    private MovieResponse mapFavoriteMoviesToMovieResponse(FavoriteMovie favoriteMovie) {
        return MovieResponse.builder()
                .id(favoriteMovie.getMovieId())
                .movieId(favoriteMovie.getMovieId())
                .userId(favoriteMovie.getUserId())
                .build();
    }

    public MovieResponse getMyFavoriteMovie(Long id) {
        log.info("getMyFavoriteMovie with id {}", id);

        FavoriteMovie favoriteMovie = favoriteMovieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException("Favorite movie not found"));

        if (favoriteMovie.getUserId() != 1L) {
            throw new RuntimeException("Favorite movie not found");
        }

        // Call the movie-service to get the favorite movie

        return mapFavoriteMoviesToMovieResponse(favoriteMovie);
    }

    public void addMyFavoriteMovie(Long id) {
        log.info("addMyFavoriteMovie with id {}", id);

        if (favoriteMovieRepository.existsByUserIdAndMovieId(1L, id)) {
            throw new RuntimeException("Favorite movie already exists");
        }

        // Call the movie-service to validate if the movie exists

        User user = User.builder()
                .id(1L)
                .build();

        FavoriteMovie favoriteMovie = FavoriteMovie.builder()
                .movieId(id)
                .user(user)
                .build();

        favoriteMovieRepository.save(favoriteMovie);
    }

    public void deleteMyFavoriteMovie(Long id) {
        log.info("deleteMyFavoriteMovie with id {}", id);

        FavoriteMovie favoriteMovie = favoriteMovieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException("Favorite movie not found"));


        if (favoriteMovie.getUserId() != 1L) {
                throw new RuntimeException("Favorite movie not found");
        }

        favoriteMovieRepository.delete(favoriteMovie);
    }
}
