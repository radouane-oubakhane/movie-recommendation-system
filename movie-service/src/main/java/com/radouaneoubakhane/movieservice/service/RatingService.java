package com.radouaneoubakhane.movieservice.service;

import com.radouaneoubakhane.movieservice.dto.Rating.RatingRequest;
import com.radouaneoubakhane.movieservice.dto.Rating.RatingResponse;

import java.util.List;

public interface RatingService {
    List<RatingResponse> getRatings();

    RatingResponse getRating(Long id);

    RatingResponse createRating(RatingRequest ratingRequest);

    RatingResponse updateRating(Long id, RatingRequest ratingRequest);

    void deleteRating(Long id);

    List<RatingResponse> getRatingsByMovieId(Long movieId);
}
