package com.radouaneoubakhane.userservice.controller;


import com.radouaneoubakhane.userservice.dto.movie.SavedMovieResponse;
import com.radouaneoubakhane.userservice.service.SavedMovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/saved/movies")
public class SavedMovieController {

    private final SavedMovieService savedMovieService;
    
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<SavedMovieResponse> getMySavedMovies() {
        return savedMovieService.getMySavedMovies();
    }
    
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SavedMovieResponse getMySavedMovie(@PathVariable Long id) {
        return savedMovieService.getMySavedMovie(id);
    }
    
    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addMySavedMovie(@PathVariable Long id) {
        savedMovieService.addMySavedMovie(id);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMySavedMovie(@PathVariable Long id) {
        savedMovieService.deleteMySavedMovie(id);
    }
}
