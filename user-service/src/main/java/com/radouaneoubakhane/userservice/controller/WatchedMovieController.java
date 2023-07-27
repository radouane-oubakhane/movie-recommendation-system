package com.radouaneoubakhane.userservice.controller;


import com.radouaneoubakhane.userservice.dto.movie.WatchedMovieResponse;
import com.radouaneoubakhane.userservice.service.WatchedMovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/watched/movies")
public class WatchedMovieController {
    private final WatchedMovieService watchedMovieService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<WatchedMovieResponse> getAllWatchedMovies() {
        return watchedMovieService.getAllWatchedMovies();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public WatchedMovieResponse getMyWatchedMovie(@PathVariable Long id) {
        return watchedMovieService.getMyWatchedMovie(id);
    }

    @PostMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addWatchedMovie(@PathVariable Long id) {
        watchedMovieService.addWatchedMovie(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteWatchedMovie(@PathVariable Long id) {
        watchedMovieService.deleteWatchedMovie(id);
    }

}
