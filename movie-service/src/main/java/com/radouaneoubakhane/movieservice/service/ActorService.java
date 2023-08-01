package com.radouaneoubakhane.movieservice.service;

import com.radouaneoubakhane.movieservice.dto.actor.ActorRequest;
import com.radouaneoubakhane.movieservice.dto.actor.ActorResponse;
import com.radouaneoubakhane.movieservice.dto.actor.MovieResponse;

import java.util.List;

public interface ActorService {
    List<ActorResponse> getActors();

    ActorResponse getActor(Long id);

    ActorResponse createActor(ActorRequest actorRequest);

    ActorResponse updateActor(Long id, ActorRequest actorRequest);

    void deleteActor(Long id);

    List<ActorResponse> searchActors(String name);

    List<MovieResponse> getActorMovies(Long actorId);

    void addMovieToActor(Long actorId, Long movieId);

    void removeMovieFromActor(Long actorId, Long movieId);

    List<ActorResponse> getActorsByIds(List<Long> id);
}

