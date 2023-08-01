package com.radouaneoubakhane.movieservice.mapper;


public class RatingMapper {
    public static com.radouaneoubakhane.movieservice.dto.rating.MovieResponse
    map(com.radouaneoubakhane.movieservice.dto.movie.MovieResponse movieResponse) {
        return com.radouaneoubakhane.movieservice.dto.rating.MovieResponse.builder()
                .id(movieResponse.getId())
                .title(movieResponse.getTitle())
                .averageRating(movieResponse.getAverageRating())
                .genre(movieResponse.getGenre())
                .build();
    }

}
