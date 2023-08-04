package com.radouaneoubakhane.movieservice.service;

import com.radouaneoubakhane.movieservice.dto.actor.ActorRequest;
import com.radouaneoubakhane.movieservice.dto.actor.ActorResponse;
import com.radouaneoubakhane.movieservice.dto.actor.MovieResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ActorService {
    List<ActorResponse> getActors();
    Page<ActorResponse> getActors(Pageable pageable);

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

