package com.radouaneoubakhane.catalogservice.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.radouaneoubakhane.catalogservice.config.KafkaConfigProps;
import com.radouaneoubakhane.catalogservice.event.GenerateRecommendationsForAllUsersEvent;
import com.radouaneoubakhane.catalogservice.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
@Slf4j
public class RecommendationServiceImpl implements RecommendationService {

    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaConfigProps kafkaConfigProps;


    @Override
    public void generateRecommendationsForAllUsers() {

        log.info("Sending event to generate recommendations for all users");


        final GenerateRecommendationsForAllUsersEvent event = GenerateRecommendationsForAllUsersEvent.builder()
                .dateTime(LocalDateTime.now())
                .build();

        try {
            final String payload = objectMapper.writeValueAsString(event);

            kafkaTemplate.send(
                    kafkaConfigProps.getTopics().get(
                            "generateRecommendationsForAllUsersTopic"),
                    payload
            );

            log.info("Event sent to generate recommendations for all users topic successfully");


        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}


