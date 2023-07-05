package com.radouaneoubakhane.movieservice.controller;


import com.radouaneoubakhane.movieservice.dto.Movie.ActorResponse;
import com.radouaneoubakhane.movieservice.dto.Movie.MovieRequest;
import com.radouaneoubakhane.movieservice.dto.Movie.MovieResponse;
import com.radouaneoubakhane.movieservice.enums.Genre;
import com.radouaneoubakhane.movieservice.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/movie")
public class MovieController {

    private final MovieService movieService;

    // CRUD operations
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MovieResponse> getMovies() {
        return movieService.getMovies();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MovieResponse getMovie(@PathVariable Long id) {
        return movieService.getMovie(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public MovieResponse createMovie(@RequestBody MovieRequest movieRequest) {
        return movieService.createMovie(movieRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MovieResponse updateMovie(@PathVariable Long id, @RequestBody MovieRequest movieRequest) {
        return movieService.updateMovie(id, movieRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
    }

    // Other operations

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<MovieResponse> searchMovies(@RequestParam String title) {
        return movieService.searchMovies(title);
    }

    @GetMapping("/searchByGenre")
    @ResponseStatus(HttpStatus.OK)
    public List<MovieResponse> searchMoviesByGenre(@RequestParam Genre genre) {
        return movieService.searchMoviesByGenre(genre);
    }


    @GetMapping("/{movieId}/actors")
    @ResponseStatus(HttpStatus.OK)
    public List<ActorResponse> getMovieActors(@PathVariable Long movieId) {
        return movieService.getMovieActors(movieId);
    }

    @PutMapping("/{movieId}/actors/{actorId}")
    @ResponseStatus(HttpStatus.OK)
    public void addActorToMovie(@PathVariable Long movieId, @PathVariable Long actorId) {
        movieService.addActorToMovie(movieId, actorId);
    }

    @DeleteMapping("/{movieId}/actors/{actorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeActorFromMovie(@PathVariable Long movieId, @PathVariable Long actorId) {
        movieService.removeActorFromMovie(movieId, actorId);
    }


}

