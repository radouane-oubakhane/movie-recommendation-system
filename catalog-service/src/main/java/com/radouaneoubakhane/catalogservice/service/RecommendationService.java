package com.radouaneoubakhane.catalogservice.service;

import com.radouaneoubakhane.catalogservice.dto.movie.MovieRecommendationResponse;

import java.util.List;

public interface RecommendationService {
    List<MovieRecommendationResponse> getRecommendedMovies(Long userId);
}
