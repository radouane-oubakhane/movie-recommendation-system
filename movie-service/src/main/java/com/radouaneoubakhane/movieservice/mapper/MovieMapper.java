package com.radouaneoubakhane.movieservice.mapper;


public class MovieMapper {

    public static com.radouaneoubakhane.movieservice.dto.movie.ActorResponse
    map(com.radouaneoubakhane.movieservice.dto.actor.ActorResponse actorResponse) {
        return com.radouaneoubakhane.movieservice.dto.movie.ActorResponse.builder()
                .id(actorResponse.getId())
                .firstName(actorResponse.getFirstName())
                .lastName(actorResponse.getLastName())
                .picture(actorResponse.getPicture())
                .build();
    }


}
