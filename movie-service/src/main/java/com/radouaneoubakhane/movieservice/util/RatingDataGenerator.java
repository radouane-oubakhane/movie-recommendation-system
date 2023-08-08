package com.radouaneoubakhane.movieservice.util;

import com.radouaneoubakhane.movieservice.domain.Rating;

import java.util.List;

public class RatingDataGenerator {

    public static Rating generateRating() {
        return Rating.builder()
                .id(1L)
                .userId(1L)
                .rating(5)
                .review("Awesome movie")
                .timestamp("2021-08-01 00:00:00")
                .movie(MovieDataGenerator.generateMovie())
                .build();
    }

    public static List<Rating> generateRatings() {
        return null;
    }
}
