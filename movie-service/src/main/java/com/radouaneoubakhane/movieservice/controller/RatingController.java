package com.radouaneoubakhane.movieservice.controller;

import com.radouaneoubakhane.movieservice.dto.rating.RatingRequest;
import com.radouaneoubakhane.movieservice.dto.rating.RatingResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RatingController {
    // Get all with pagination and sorting

    Page<RatingResponse> getRatingsWithPaginationAndSorting(Integer pageNo, Integer pageSize, String[] sortBy);


    List<RatingResponse> getRatings();


    RatingResponse getRating(Long id);


    RatingResponse createRating(RatingRequest ratingRequest);

    RatingResponse updateRating(Long id, RatingRequest ratingRequest);


    void deleteRating(Long id);

    List<RatingResponse> getRatingsByMovieId(Long movieId);
}
