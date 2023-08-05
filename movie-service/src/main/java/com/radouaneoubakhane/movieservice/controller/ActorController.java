package com.radouaneoubakhane.movieservice.controller;

import com.radouaneoubakhane.movieservice.dto.actor.ActorRequest;
import com.radouaneoubakhane.movieservice.dto.actor.ActorResponse;
import com.radouaneoubakhane.movieservice.dto.actor.MovieResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ActorController {
    // CRUD operations
    Page<ActorResponse> getActorsWithPaginationAndSorting(Integer pageNo, Integer pageSize, String[] sortBy);

    List<ActorResponse> getActors();

    ActorResponse getActor(Long id);

    ActorResponse createActor(ActorRequest actorRequest);

    ActorResponse updateActor(Long id, ActorRequest actorRequest);

    void deleteActor(Long id);

    // Other operations
    List<ActorResponse> searchActors(String name);

    List<MovieResponse> getMoviesByActor(Long actorId);

    void addMovieToActor(Long actorId,Long movieId);


    void removeMovieFromActor(Long actorId, Long movieId);

    List<ActorResponse> getActorsByIds(List<Long> id);
}
