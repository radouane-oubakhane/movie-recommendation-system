package com.radouaneoubakhane.userservice.service.impl;


import com.radouaneoubakhane.userservice.dto.movie.FavoriteMovieResponse;
import com.radouaneoubakhane.userservice.dto.movie.MovieResponse;
import com.radouaneoubakhane.userservice.exception.movie.MovieNotFoundException;
import com.radouaneoubakhane.userservice.model.FavoriteMovie;
import com.radouaneoubakhane.userservice.model.User;
import com.radouaneoubakhane.userservice.repository.FavoriteMovieRepository;
import com.radouaneoubakhane.userservice.service.FavoriteMovieService;
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
public class FavoriteMovieServiceImpl implements FavoriteMovieService {

    final private FavoriteMovieRepository favoriteMovieRepository;
    private final WebClient.Builder webClientBuilder;

    public List<FavoriteMovieResponse> getMyFavoriteMovies() {
        log.info("getMyFavoriteMovies");

        List<FavoriteMovie> favoriteMovies = favoriteMovieRepository.findAllByUserId(1L);

        // Call the movie-service to get the favorite movies
        // http://movie-service/api/v1/movie/ids?id=id
        List<MovieResponse> result = webClientBuilder.build()
                .get()
                .uri(
                        "lb://movie-service/api/v1/movies/ids",
                                uriBuilder -> uriBuilder
                                .queryParam("id", favoriteMovies.stream()
                                        .map(FavoriteMovie::getMovieId)
                                        .toList())
                                .build()
                        )
                .retrieve()
                .bodyToFlux(MovieResponse.class)
                .collectList()
                .block();

        return mapToFavoriteMovieResponse(favoriteMovies, result);

    }

    private List<FavoriteMovieResponse> mapToFavoriteMovieResponse(List<FavoriteMovie> favoriteMovies, List<MovieResponse> result) {
        return favoriteMovies.stream()
                .map(favoriteMovie -> {
                    MovieResponse movieResponse = result.stream()
                            .filter(movie -> movie.getId().equals(favoriteMovie.getMovieId()))
                            .findFirst()
                            .orElseThrow(() -> new MovieNotFoundException("Favorite movie not found"));

                    return FavoriteMovieResponse.builder()
                            .id(favoriteMovie.getId())
                            .movie(movieResponse)
                            .build();
                })
                .toList();
    }


    public FavoriteMovieResponse getMyFavoriteMovie(Long id) {
        log.info("getMyFavoriteMovie with id {}", id);

        FavoriteMovie favoriteMovie = favoriteMovieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException("Favorite movie not found"));

        if (favoriteMovie.getUser().getId() != 1L) {
            throw new IllegalArgumentException("Favorite movie not found");
        }

        // Call the movie-service to get the favorite movie
        // http://movie-service/api/v1/movie/{id}
        MovieResponse result = webClientBuilder.build()
                .get()
                .uri(
                        "lb://movie-service/api/v1/movies/{id}",
                        uriBuilder -> uriBuilder
                                .build(favoriteMovie.getMovieId())
                )
                .retrieve()
                .bodyToMono(MovieResponse.class)
                .block();

        return FavoriteMovieResponse.builder()
                .id(favoriteMovie.getId())
                .movie(result)
                .build();
    }

    public void addMyFavoriteMovie(Long id) {
        log.info("addMyFavoriteMovie with id {}", id);

        if (favoriteMovieRepository.existsByUserIdAndMovieId(1L, id)) {
            throw new IllegalArgumentException("Favorite movie already exists");
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


        if (favoriteMovie.getUser().getId() != 1L) {
                throw new IllegalArgumentException("Favorite movie not found");
        }

        favoriteMovieRepository.delete(favoriteMovie);
    }
}
