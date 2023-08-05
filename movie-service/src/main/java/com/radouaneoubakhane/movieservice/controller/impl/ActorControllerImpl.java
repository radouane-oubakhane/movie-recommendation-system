package com.radouaneoubakhane.movieservice.controller.impl;


import com.radouaneoubakhane.movieservice.dto.actor.ActorRequest;
import com.radouaneoubakhane.movieservice.dto.actor.ActorResponse;
import com.radouaneoubakhane.movieservice.dto.actor.MovieResponse;
import com.radouaneoubakhane.movieservice.service.ActorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/actors")
public class ActorControllerImpl implements com.radouaneoubakhane.movieservice.controller.ActorController {

    private final ActorService actorService;

    // CRUD operations
    @Override
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<ActorResponse> getActorsWithPaginationAndSorting(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "firstName, asc") String[] sortBy)
    {
        Pageable pageable = PageRequest.of(pageNo, pageSize, getSortOrder(sortBy));
        return actorService.getActors(pageable);
    }

    @Override
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<ActorResponse> getActors() {
        return actorService.getActors();
    }

    @Override
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ActorResponse getActor(@PathVariable Long id) {
        return actorService.getActor(id);
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ActorResponse createActor(@RequestBody ActorRequest actorRequest) {
        return actorService.createActor(actorRequest);
    }

    @Override
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ActorResponse updateActor(@PathVariable Long id, @RequestBody ActorRequest actorRequest) {
        return actorService.updateActor(id, actorRequest);
    }


    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteActor(@PathVariable Long id) {
        actorService.deleteActor(id);
    }

    // Other operations
    @Override
    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<ActorResponse> searchActors(@RequestParam String name) {
        return actorService.searchActors(name);
    }

    @Override
    @GetMapping("/{actorId}/movies")
    @ResponseStatus(HttpStatus.OK)
    public List<MovieResponse> getMoviesByActor(@PathVariable Long actorId) {
        return actorService.getActorMovies(actorId);
    }

    @Override
    @PutMapping("/{actorId}/movies/{movieId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addMovieToActor(@PathVariable Long actorId, @PathVariable Long movieId) {
        actorService.addMovieToActor(actorId, movieId);
    }

    @Override
    @DeleteMapping("/{actorId}/movies/{movieId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeMovieFromActor(@PathVariable Long actorId, @PathVariable Long movieId) {
        actorService.removeMovieFromActor(actorId, movieId);
    }


    // Endpoints for the user-service

    @Override
    @GetMapping("/ids")
    @ResponseStatus(HttpStatus.OK)
    public List<ActorResponse> getActorsByIds(@RequestParam List<Long> id) {
        return actorService.getActorsByIds(id);
    }

    // Helper methods for sorting and pagination ================================
    // ==========================================================================
    private Sort getSortOrder(String[] sort) {
        if (sort.length > 1) {
            String sortBy = sort[0];
            String sortOrder = sort[1].equalsIgnoreCase("desc") ? "desc" : "asc";
            return Sort.by(Sort.Direction.fromString(sortOrder), sortBy);
        } else if (sort.length == 1) {
            String sortBy = sort[0];
            return Sort.by(Sort.Direction.ASC, sortBy);
        }
        return Sort.unsorted();
    }
}


