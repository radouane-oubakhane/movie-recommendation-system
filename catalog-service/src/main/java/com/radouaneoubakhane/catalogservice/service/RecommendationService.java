package com.radouaneoubakhane.catalogservice.service;

import com.radouaneoubakhane.catalogservice.dto.MovieRecommendationResponse;

import java.util.List;

public interface RecommendationService {
    List<MovieRecommendationResponse> getRecommendedMovies(Long userId);
}
