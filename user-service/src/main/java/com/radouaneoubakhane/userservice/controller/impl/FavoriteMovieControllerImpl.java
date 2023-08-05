package com.radouaneoubakhane.userservice.controller.impl;


import com.radouaneoubakhane.userservice.dto.movie.FavoriteMovieResponse;
import com.radouaneoubakhane.userservice.service.FavoriteMovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/favorite/movies")
public class FavoriteMovieControllerImpl implements com.radouaneoubakhane.userservice.controller.FavoriteMovieController {

    final private FavoriteMovieService favoriteMovieService;


    @Override
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<FavoriteMovieResponse> getMyFavoriteMovies() {
        return favoriteMovieService.getMyFavoriteMovies();
    }

    @Override
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FavoriteMovieResponse getMyFavoriteMovie(@PathVariable Long id) {
        return favoriteMovieService.getMyFavoriteMovie(id);
    }

    @Override
    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addMyFavoriteMovie(@PathVariable Long id) {
        favoriteMovieService.addMyFavoriteMovie(id);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMyFavoriteMovie(@PathVariable Long id) {
        favoriteMovieService.deleteMyFavoriteMovie(id);
    }
}
