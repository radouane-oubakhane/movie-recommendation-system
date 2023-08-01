package com.radouaneoubakhane.movieservice.mapper;


public class DirectorMapper {
    public static com.radouaneoubakhane.movieservice.dto.actor.DirectorResponse
        map(com.radouaneoubakhane.movieservice.dto.movie.DirectorResponse directorResponse) {
        return com.radouaneoubakhane.movieservice.dto.actor.DirectorResponse.builder()
                .id(directorResponse.getId())
                .firstName(directorResponse.getFirstName())
                .lastName(directorResponse.getLastName())
                .build();
    }

    public static com.radouaneoubakhane.movieservice.dto.director.MovieResponse
    map(com.radouaneoubakhane.movieservice.dto.movie.MovieResponse movieResponse) {
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
