package com.radouaneoubakhane.catalogservice.controller.impl;

import com.radouaneoubakhane.catalogservice.controller.RecommendationController;
import com.radouaneoubakhane.catalogservice.dto.MovieRecommendationResponse;
import com.radouaneoubakhane.catalogservice.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/recommendations/movies")
public class RecommendationControllerImpl implements RecommendationController {

    private final RecommendationService recommendationService;


    @Override
    @GetMapping("/{userId}")
    public ResponseEntity<List<MovieRecommendationResponse>> getRecommendedMovies(
            @PathVariable Long userId)
    {
        return ResponseEntity.ok(
                recommendationService.getRecommendedMovies(userId)
        );
    }








}
