package com.radouaneoubakhane.userservice.controller;


import com.radouaneoubakhane.userservice.dto.movie.FavoriteMovieResponse;
import com.radouaneoubakhane.userservice.service.FavoriteMovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/favorite/movies")
public class FavoriteMovieController {

    final private FavoriteMovieService favoriteMovieService;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<FavoriteMovieResponse> getMyFavoriteMovies() {
        return favoriteMovieService.getMyFavoriteMovies();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FavoriteMovieResponse getMyFavoriteMovie(@PathVariable Long id) {
        return favoriteMovieService.getMyFavoriteMovie(id);
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addMyFavoriteMovie(@PathVariable Long id) {
        favoriteMovieService.addMyFavoriteMovie(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMyFavoriteMovie(@PathVariable Long id) {
        favoriteMovieService.deleteMyFavoriteMovie(id);
    }
}
