package com.radouaneoubakhane.movieservice.mapper.rating;


import com.radouaneoubakhane.movieservice.dto.rating.MovieResponse;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class RatingMovieResponseMapper implements Function
        <com.radouaneoubakhane.movieservice.dto.movie.MovieResponse,
        com.radouaneoubakhane.movieservice.dto.rating.MovieResponse>
{

    @Override
    public MovieResponse
    apply(com.radouaneoubakhane.movieservice.dto.movie.MovieResponse movieResponse
    ) {
        return com.radouaneoubakhane.movieservice.dto.rating.MovieResponse.builder()
                .id(movieResponse.getId())
                .title(movieResponse.getTitle())
                .averageRating(movieResponse.getAverageRating())
                .genre(movieResponse.getGenre())
                .build();
    }
}
