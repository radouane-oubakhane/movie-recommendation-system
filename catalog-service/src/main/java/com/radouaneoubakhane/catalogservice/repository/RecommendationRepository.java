package com.radouaneoubakhane.catalogservice.repository;

import com.radouaneoubakhane.catalogservice.model.Recommendation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RecommendationRepository extends MongoRepository<Recommendation, String> {

    List<Recommendation> findByUserId(Long userId);
}
