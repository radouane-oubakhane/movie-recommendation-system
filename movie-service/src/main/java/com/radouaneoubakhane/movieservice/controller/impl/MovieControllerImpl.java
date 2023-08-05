package com.radouaneoubakhane.movieservice.controller.impl;


import com.radouaneoubakhane.movieservice.dto.movie.ActorResponse;
import com.radouaneoubakhane.movieservice.dto.movie.MovieRequest;
import com.radouaneoubakhane.movieservice.dto.movie.MovieResponse;
import com.radouaneoubakhane.movieservice.enums.Genre;
import com.radouaneoubakhane.movieservice.service.MovieService;
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
@RequestMapping("/api/v1/movies")
public class MovieControllerImpl implements com.radouaneoubakhane.movieservice.controller.MovieController {

    private final MovieService movieService;

    // CRUD operations ==========================================================
    // ==========================================================================
    @Override
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<MovieResponse> getMoviesWithPaginationAndSorting(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "title,asc") String[] sortBy)
    {
        Pageable pageable = PageRequest.of(pageNo, pageSize, getSortOrder(sortBy));
        return movieService.getMovies(pageable);
    }


    @Override
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<MovieResponse> getMovies() {
        return movieService.getMovies();
    }

    @Override
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MovieResponse getMovie(@PathVariable Long id) {
        return movieService.getMovie(id);
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public MovieResponse createMovie(@RequestBody MovieRequest movieRequest) {
        return movieService.createMovie(movieRequest);
    }

    @Override
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MovieResponse updateMovie(@PathVariable Long id, @RequestBody MovieRequest movieRequest) {
        return movieService.updateMovie(id, movieRequest);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
    }

    // Other operations

    @Override
    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<MovieResponse> searchMovies(@RequestParam String title) {
        return movieService.searchMovies(title);
    }

    @Override
    @GetMapping("/searchByGenre")
    @ResponseStatus(HttpStatus.OK)
    public List<MovieResponse> searchMoviesByGenre(@RequestParam Genre genre) {
        return movieService.searchMoviesByGenre(genre);
    }


    @Override
    @GetMapping("/{movieId}/actors")
    @ResponseStatus(HttpStatus.OK)
    public List<ActorResponse> getMovieActors(@PathVariable Long movieId) {
        return movieService.getMovieActors(movieId);
    }

    @Override
    @PutMapping("/{movieId}/actors/{actorId}")
    @ResponseStatus(HttpStatus.OK)
    public void addActorToMovie(@PathVariable Long movieId, @PathVariable Long actorId) {
        movieService.addActorToMovie(movieId, actorId);
    }

    @Override
    @DeleteMapping("/{movieId}/actors/{actorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeActorFromMovie(@PathVariable Long movieId, @PathVariable Long actorId) {
        movieService.removeActorFromMovie(movieId, actorId);
    }

    // Endpoints for the user-service ===========================================
    // ==========================================================================
    @Override
    @GetMapping("/ids")
    @ResponseStatus(HttpStatus.OK)
    public List<MovieResponse> getMoviesByIds(@RequestParam List<Long> id) {
        return movieService.getMoviesByIds(id);
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

