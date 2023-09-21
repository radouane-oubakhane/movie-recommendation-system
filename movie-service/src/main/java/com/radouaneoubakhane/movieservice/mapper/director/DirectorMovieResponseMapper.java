package com.radouaneoubakhane.movieservice.mapper.director;

import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class DirectorMovieResponseMapper implements Function
        <com.radouaneoubakhane.movieservice.dto.movie.MovieResponse,
        com.radouaneoubakhane.movieservice.dto.director.MovieResponse>
{

    @Override
    public com.radouaneoubakhane.movieservice.dto.director.MovieResponse
    apply(com.radouaneoubakhane.movieservice.dto.movie.MovieResponse movieResponse) {
        return com.radouaneoubakhane.movieservice.dto.director.MovieResponse.builder()
                .id(movieResponse.getId())
                .title(movieResponse.getTitle())
                .poster(movieResponse.getPoster())
                .releaseDate(movieResponse.getReleaseDate())
                .averageRating(movieResponse.getAverageRating())
                .genre(movieResponse.getGenre())
                .build();
    }
}
