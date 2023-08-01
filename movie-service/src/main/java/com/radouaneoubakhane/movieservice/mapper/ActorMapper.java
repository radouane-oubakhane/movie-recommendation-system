package com.radouaneoubakhane.movieservice.mapper;



public class ActorMapper {
    public static com.radouaneoubakhane.movieservice.dto.actor.MovieResponse
    map(com.radouaneoubakhane.movieservice.dto.movie.MovieResponse movieResponse) {
        return com.radouaneoubakhane.movieservice.dto.actor.MovieResponse.builder()
                .id(movieResponse.getId())
                .title(movieResponse.getTitle())
                .poster(movieResponse.getPoster())
                .releaseDate(movieResponse.getReleaseDate())
                .averageRating(movieResponse.getAverageRating())
                .director(DirectorMapper.map(movieResponse.getDirector()))
                .genre(movieResponse.getGenre())
                .build();
    }
}
