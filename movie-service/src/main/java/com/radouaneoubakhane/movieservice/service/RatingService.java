package com.radouaneoubakhane.movieservice.service;

import com.radouaneoubakhane.movieservice.dto.rating.RatingRequest;
import com.radouaneoubakhane.movieservice.dto.rating.RatingResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RatingService {
    List<RatingResponse> getRatings();

    Page<RatingResponse> getRatings(Pageable pageable);

    RatingResponse getRating(Long id);

    RatingResponse createRating(RatingRequest ratingRequest);

    RatingResponse updateRating(Long id, RatingRequest ratingRequest);

    void deleteRating(Long id);

    List<RatingResponse> getRatingsByMovieId(Long movieId);

}
