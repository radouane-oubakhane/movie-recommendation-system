package com.radouaneoubakhane.userservice.controller.impl;


import com.radouaneoubakhane.userservice.dto.movie.WatchlistMovieResponse;
import com.radouaneoubakhane.userservice.service.WatchlistMovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/watchlist/movies")
public class WatchlistMovieControllerImpl implements com.radouaneoubakhane.userservice.controller.WatchlistMovieController {
    private final WatchlistMovieService watchlistMovieService;

    @Override
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<WatchlistMovieResponse> getAllWatchlistMovies() {
        return watchlistMovieService.getAllWatchlistMovies();
    }

    @Override
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public WatchlistMovieResponse getMyWatchlistMovie(@PathVariable Long id) {
        return watchlistMovieService.getMyWatchlistMovie(id);
    }

    @Override
    @PostMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addWatchlistMovie(@PathVariable Long id) {
        watchlistMovieService.addWatchlistMovie(id);
    }

    @Override
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteWatchlistMovie(@PathVariable Long id) {
        watchlistMovieService.deleteWatchlistMovie(id);
    }
}
