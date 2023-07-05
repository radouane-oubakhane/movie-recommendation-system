package com.radouaneoubakhane.movieservice.controller;


import com.radouaneoubakhane.movieservice.dto.Rating.RatingRequest;
import com.radouaneoubakhane.movieservice.dto.Rating.RatingResponse;
import com.radouaneoubakhane.movieservice.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/rating")
public class RatingController {

    private final RatingService ratingService;

    // CRUD operations
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RatingResponse> getRatings() {
        return ratingService.getRatings();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RatingResponse getRating(@PathVariable Long id) {
        return ratingService.getRating(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public RatingResponse createRating(@RequestBody RatingRequest ratingRequest) {
        return ratingService.createRating(ratingRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RatingResponse updateRating(@PathVariable Long id, @RequestBody RatingRequest ratingRequest) {
        return ratingService.updateRating(id, ratingRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRating(@PathVariable Long id) {
        ratingService.deleteRating(id);
    }

    // Other operations

    @GetMapping("/movie/{movieId}")
    @ResponseStatus(HttpStatus.OK)
    public List<RatingResponse> getRatingsByMovieId(@PathVariable Long movieId) {
        return ratingService.getRatingsByMovieId(movieId);
    }
}
