package com.radouaneoubakhane.movieservice.service.impl;


import com.radouaneoubakhane.movieservice.dto.rating.MovieResponse;
import com.radouaneoubakhane.movieservice.dto.rating.RatingRequest;
import com.radouaneoubakhane.movieservice.dto.rating.RatingResponse;
import com.radouaneoubakhane.movieservice.model.Movie;
import com.radouaneoubakhane.movieservice.model.Rating;
import com.radouaneoubakhane.movieservice.exception.Movie.MovieNotFoundException;
import com.radouaneoubakhane.movieservice.exception.Rating.RatingNotFoundException;
import com.radouaneoubakhane.movieservice.mapper.RatingMapper;
import com.radouaneoubakhane.movieservice.repository.RatingRepository;
import com.radouaneoubakhane.movieservice.service.MovieService;
import com.radouaneoubakhane.movieservice.service.RatingService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;
    private final MovieService movieService;



    public List<RatingResponse> getRatings() {
        log.info("Fetching all ratings");

        List<Rating> ratings = ratingRepository.findAll();

        return ratings.stream().map(this::mapRatingToRatingResponse).toList();
    }

    @Override
    public Page<RatingResponse> getRatings(Pageable pageable) {
        log.info("Fetching all ratings");

        Page<Rating> ratings = ratingRepository.findAll(pageable);

        return ratings.map(this::mapRatingToRatingResponse);
    }

    private RatingResponse mapRatingToRatingResponse(Rating rating) {
        return RatingResponse.builder()
                .id(rating.getId())
                .userId(rating.getUserId())
                .rating(rating.getRating())
                .review(rating.getReview())
                .timestamp(rating.getTimestamp())
                .movie(mapMovieToMovieResponse(rating.getMovie()))
                .build();
    }

    private MovieResponse mapMovieToMovieResponse(Movie movie) {
        return MovieResponse.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .averageRating(movie.getAverageRating())
                .genre(movie.getGenre())
                .build();
    }


    public RatingResponse getRating(Long id) {
        log.info("Fetching rating with id {}", id);

        Rating rating = ratingRepository.findById(id).orElseThrow(
                () -> new RatingNotFoundException("Rating not found")
        );

        return mapRatingToRatingResponse(rating);
    }

    public RatingResponse createRating(RatingRequest ratingRequest) {
        log.info("Creating rating");

        MovieResponse movieResponse = RatingMapper.map(
                movieService.getMovie(ratingRequest.getMovieId())
        );

        if (movieResponse == null) {
            throw new MovieNotFoundException("Movie not found");
        }

        Rating rating = Rating.builder()
                .userId(ratingRequest.getUserId())
                .rating(ratingRequest.getRating())
                .review(ratingRequest.getReview())
                .timestamp(ratingRequest.getTimestamp())
                .movie(
                        Movie.builder()
                                .id(movieResponse.getId())
                                .title(movieResponse.getTitle())
                                .averageRating(movieResponse.getAverageRating())
                                .genre(movieResponse.getGenre())
                                .build()
                )
                .build();

        return mapRatingToRatingResponse(ratingRepository.save(rating));
    }

    public RatingResponse updateRating(Long id, RatingRequest ratingRequest) {
        log.info("Updating rating with id {}", id);

        Rating rating = ratingRepository.findById(id).orElseThrow(
                () -> new RatingNotFoundException("Rating not found")
        );

        rating.setUserId(ratingRequest.getUserId());
        rating.setRating(ratingRequest.getRating());
        rating.setReview(ratingRequest.getReview());
        rating.setTimestamp(ratingRequest.getTimestamp());

        return mapRatingToRatingResponse(ratingRepository.save(rating));
    }


    public void deleteRating(Long id) {
        log.info("Deleting rating with id {}", id);

        Rating rating = ratingRepository.findById(id).orElseThrow(
                () -> new RatingNotFoundException("Rating not found")
        );

        ratingRepository.delete(rating);
    }

    public List<RatingResponse> getRatingsByMovieId(Long movieId) {
        log.info("Fetching ratings by movie id {}", movieId);

        if (!movieService.existsById(movieId)) {
            throw new MovieNotFoundException("Movie not found");
        }

        List<Rating> ratings = ratingRepository.findByMovieId(movieId);

        return ratings.stream().map(this::mapRatingToRatingResponse).toList();
    }
}
