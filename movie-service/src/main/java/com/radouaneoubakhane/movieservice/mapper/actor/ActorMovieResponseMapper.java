package com.radouaneoubakhane.movieservice.mapper.actor;


import com.radouaneoubakhane.movieservice.mapper.director.DirectorResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class ActorMovieResponseMapper implements Function
        <com.radouaneoubakhane.movieservice.dto.movie.MovieResponse,
        com.radouaneoubakhane.movieservice.dto.actor.MovieResponse>
    {
        private final DirectorResponseMapper directorResponseMapper;

        @Override
        public com.radouaneoubakhane.movieservice.dto.actor.MovieResponse
            apply(com.radouaneoubakhane.movieservice.dto.movie.MovieResponse movieResponse)
        {
            return com.radouaneoubakhane.movieservice.dto.actor.MovieResponse.builder()
                    .id(movieResponse.getId())
                    .title(movieResponse.getTitle())
                    .poster(movieResponse.getPoster())
                    .releaseDate(movieResponse.getReleaseDate())
                    .averageRating(movieResponse.getAverageRating())
                    .director(directorResponseMapper.apply(movieResponse.getDirector()))
                    .genre(movieResponse.getGenre())
                    .build();
        }
    }


