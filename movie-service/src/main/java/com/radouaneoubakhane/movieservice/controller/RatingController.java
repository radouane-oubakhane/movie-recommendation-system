package com.radouaneoubakhane.movieservice.controller;


import com.radouaneoubakhane.movieservice.dto.rating.RatingRequest;
import com.radouaneoubakhane.movieservice.dto.rating.RatingResponse;
import com.radouaneoubakhane.movieservice.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/ratings")
public class RatingController {

    private final RatingService ratingService;

    // CRUD operations

    // Get all with pagination and sorting
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<RatingResponse> getRatingsWithPaginationAndSorting(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "timestamp, asc") String[] sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, getSortOrder(sortBy));
        return ratingService.getRatings(pageable);
    }



    @GetMapping("/all")
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

    // Helper methods for sorting and pagination ================================
    // ==========================================================================
    private Sort getSortOrder(String[] sort) {
        if (sort.length > 1) {
            String sortBy = sort[0];
            String sortOrder = sort[1].equalsIgnoreCase("desc") ? "desc" : "asc";
            return Sort.by(Sort.Direction.fromString(sortOrder), sortBy);
        } else if (sort.length == 1) {
            String sortBy = sort[0];
            return Sort.by(Sort.Direction.ASC, sortBy);
        }
        return Sort.unsorted();
    }
}

