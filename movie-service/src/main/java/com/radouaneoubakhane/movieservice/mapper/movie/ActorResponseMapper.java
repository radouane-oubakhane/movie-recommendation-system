package com.radouaneoubakhane.movieservice.mapper.movie;



import org.springframework.stereotype.Service;

import java.util.function.Function;


@Service
public class ActorResponseMapper implements Function
        <com.radouaneoubakhane.movieservice.dto.actor.ActorResponse,
        com.radouaneoubakhane.movieservice.dto.movie.ActorResponse>
{
    @Override
    public com.radouaneoubakhane.movieservice.dto.movie.ActorResponse
        apply(com.radouaneoubakhane.movieservice.dto.actor.ActorResponse actorResponse)
    {
        return com.radouaneoubakhane.movieservice.dto.movie.ActorResponse.builder()
                .id(actorResponse.getId())
                .firstName(actorResponse.getFirstName())
                .lastName(actorResponse.getLastName())
                .picture(actorResponse.getPicture())
                .build();
    }
}
