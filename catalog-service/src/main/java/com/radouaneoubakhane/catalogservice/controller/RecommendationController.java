package com.radouaneoubakhane.catalogservice.controller;

import com.radouaneoubakhane.catalogservice.dto.MovieRecommendationResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RecommendationController {

    ResponseEntity<List<MovieRecommendationResponse>> getRecommendedMovies(Long userId);
}
