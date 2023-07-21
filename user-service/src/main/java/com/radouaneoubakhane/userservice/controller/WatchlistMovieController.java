package com.radouaneoubakhane.userservice.controller;


import com.radouaneoubakhane.userservice.dto.movie.MovieResponse;
import com.radouaneoubakhane.userservice.service.WatchlistMovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/watchlist/movie")
public class WatchlistMovieController {
    private final WatchlistMovieService watchlistMovieService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MovieResponse> getAllWatchlistMovies() {
        return watchlistMovieService.getAllWatchlistMovies();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public MovieResponse getMyWatchlistMovie(@PathVariable Long id) {
        return watchlistMovieService.getMyWatchlistMovie(id);
    }

    @PostMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addWatchlistMovie(@PathVariable Long id) {
        watchlistMovieService.addWatchlistMovie(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteWatchlistMovie(@PathVariable Long id) {
        watchlistMovieService.deleteWatchlistMovie(id);
    }
}
