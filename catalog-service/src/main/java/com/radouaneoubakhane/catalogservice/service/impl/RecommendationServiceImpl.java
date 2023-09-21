package com.radouaneoubakhane.catalogservice.service.impl;

import com.radouaneoubakhane.catalogservice.dto.MovieResponse;
import com.radouaneoubakhane.catalogservice.dto.MovieRecommendationResponse;
import com.radouaneoubakhane.catalogservice.model.Recommendation;
import com.radouaneoubakhane.catalogservice.repository.RecommendationRepository;
import com.radouaneoubakhane.catalogservice.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class RecommendationServiceImpl implements RecommendationService {

    private final RecommendationRepository recommendationRepository;
    private final WebClient.Builder webClientBuilder;


    @Override
    public List<MovieRecommendationResponse> getRecommendedMovies(Long userId) {
        log.info("Fetching recommended movies for user {}", userId);

        List<Recommendation> recommendations = recommendationRepository.findByUserId(userId);

        if (recommendations.isEmpty()) {
            log.info("No recommended movies found for user {}", userId);
            return List.of();
        }

        // Call the movie-service to get the favorite movies
        // http://movie-service/api/v1/movie/ids?id=id
        List<MovieResponse> result = webClientBuilder.build()
                .get()
                .uri(
                        "lb://movie-service/api/v1/movies/ids",
                        uriBuilder -> uriBuilder
                                .queryParam("id", recommendations.stream()
                                        .map(Recommendation::getMovieId)
                                        .toList())
                                .build()
                )
                .retrieve()
                .bodyToFlux(MovieResponse.class)
                .collectList()
                .block();

        return mapToRecommendedMovieResponse(recommendations, result);
    }

    private List<MovieRecommendationResponse> mapToRecommendedMovieResponse(List<Recommendation> recommendations, List<MovieResponse> result) {
        return recommendations.stream()
                .map(recommendation -> {
                    MovieResponse movieResponse = result.stream()
                            .filter(movie -> movie.getId().equals( recommendation.getMovieId()))
                            .findFirst()
                            .orElse(null);

                    return MovieRecommendationResponse.builder()
                            .id(recommendation.getId())
                            .movie(movieResponse)
                            .build();
                })
                .toList();
    }
}


