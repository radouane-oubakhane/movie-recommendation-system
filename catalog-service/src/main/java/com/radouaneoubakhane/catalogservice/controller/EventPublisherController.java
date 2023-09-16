package com.radouaneoubakhane.catalogservice.controller;

import org.springframework.http.ResponseEntity;

public interface EventPublisherController {
    ResponseEntity<String> triggerRecommendations();
}
