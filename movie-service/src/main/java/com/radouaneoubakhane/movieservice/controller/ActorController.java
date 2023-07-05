package com.radouaneoubakhane.movieservice.controller;


import com.radouaneoubakhane.movieservice.dto.Actor.ActorRequest;
import com.radouaneoubakhane.movieservice.dto.Actor.ActorResponse;
import com.radouaneoubakhane.movieservice.dto.Actor.MovieResponse;
import com.radouaneoubakhane.movieservice.service.ActorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/actor")
public class ActorController {

    private final ActorService actorService;

    // CRUD operations
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ActorResponse> getActors() {
        return actorService.getActors();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ActorResponse getActor(@PathVariable Long id) {
        return actorService.getActor(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ActorResponse createActor(@RequestBody ActorRequest actorRequest) {
        return actorService.createActor(actorRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ActorResponse updateActor(@PathVariable Long id, @RequestBody ActorRequest actorRequest) {
        return actorService.updateActor(id, actorRequest);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteActor(@PathVariable Long id) {
        actorService.deleteActor(id);
    }

    // Other operations
    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<ActorResponse> searchActors(@RequestParam String name) {
        return actorService.searchActors(name);
    }

    @GetMapping("/{actorId}/movies")
    @ResponseStatus(HttpStatus.OK)
    public List<MovieResponse> getMoviesByActor(@PathVariable Long actorId) {
        return actorService.getActorMovies(actorId);
    }

    @PutMapping("/{actorId}/movies/{movieId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addMovieToActor(@PathVariable Long actorId, @PathVariable Long movieId) {
        actorService.addMovieToActor(actorId, movieId);
    }

    @DeleteMapping("/{actorId}/movies/{movieId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeMovieFromActor(@PathVariable Long actorId, @PathVariable Long movieId) {
        actorService.removeMovieFromActor(actorId, movieId);
    }


}

