package com.radouaneoubakhane.movieservice.mapper.director;

import org.springframework.stereotype.Service;

import java.util.function.Function;


@Service
public class DirectorResponseMapper implements Function
        <com.radouaneoubakhane.movieservice.dto.movie.DirectorResponse,
        com.radouaneoubakhane.movieservice.dto.actor.DirectorResponse>

{
    @Override
    public com.radouaneoubakhane.movieservice.dto.actor.DirectorResponse
    apply(com.radouaneoubakhane.movieservice.dto.movie.DirectorResponse directorResponse) {
        return com.radouaneoubakhane.movieservice.dto.actor.DirectorResponse.builder()
                .id(directorResponse.getId())
                .firstName(directorResponse.getFirstName())
                .lastName(directorResponse.getLastName())
                .build();
    }
}
