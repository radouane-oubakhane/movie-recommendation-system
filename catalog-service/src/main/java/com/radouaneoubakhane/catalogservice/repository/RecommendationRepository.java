package com.radouaneoubakhane.catalogservice.repository;

import com.radouaneoubakhane.catalogservice.model.Recommendation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RecommendationRepository extends MongoRepository<Recommendation, String> {
}
