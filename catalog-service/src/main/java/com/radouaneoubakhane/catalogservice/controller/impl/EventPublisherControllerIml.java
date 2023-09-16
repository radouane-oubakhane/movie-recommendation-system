package com.radouaneoubakhane.catalogservice.controller.impl;


import com.radouaneoubakhane.catalogservice.controller.EventPublisherController;
import com.radouaneoubakhane.catalogservice.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/events")
public class EventPublisherControllerIml implements EventPublisherController {

    private final RecommendationService recommendationService;

    @Override
    @GetMapping("/trigger-recommendations")
    public ResponseEntity<String> triggerRecommendations() {

        recommendationService.generateRecommendationsForAllUsers();

        return ResponseEntity.ok("Recommendations request triggered successfully!");
    }
}
