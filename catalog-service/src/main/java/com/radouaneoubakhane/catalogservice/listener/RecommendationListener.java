package com.radouaneoubakhane.catalogservice.listener;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.radouaneoubakhane.catalogservice.config.KafkaConfigProps;
import com.radouaneoubakhane.catalogservice.exception.InvalidMessageException;
import com.radouaneoubakhane.catalogservice.model.Recommendation;
import com.radouaneoubakhane.catalogservice.repository.RecommendationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class RecommendationListener {

    private final RecommendationRepository recommendationRepository;
    private final ObjectMapper objectMapper;


    @KafkaListener(topics = "recommendations_generated_for_all_users")
    public void listen(String message) {
        log.info("Message received: {}", message);

        try {
            final List<HashMap<String, Object>> payload = readJsonAsMap(message);

            final List<Recommendation> recommendations = payload.stream()
                    .map(this::RecommendationFromPayload)
                    .toList();

            recommendationRepository.saveAll(recommendations);

            log.info("Recommendations saved: {}", recommendations);

        } catch (InvalidMessageException ex) {
            log.error("Invalid message received from recommendation service: {}", message);
        }
    }

    private List<HashMap<String, Object>> readJsonAsMap(String message) {
        try{
            final TypeReference<List<HashMap<String,Object>>> typeRef = new TypeReference<>() {};
            return objectMapper.readValue(message, typeRef);
        } catch(JsonProcessingException ex) {
            throw new InvalidMessageException("Invalid message received from recommendation service");
        }
    }


    private Recommendation RecommendationFromPayload(Map<String, Object> payload) {
        return Recommendation.builder()
                .userId( (Integer) payload.get("user_id"))
                .movieId( (Integer) payload.get("movie_id"))
                .rating((Double) payload.get("rating"))
                .timestamp((Integer) payload.get("timestamp"))
                .build();
    }
}


