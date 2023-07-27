package com.radouaneoubakhane.movieservice.controller;


import com.radouaneoubakhane.movieservice.dto.Director.DirectorRequest;
import com.radouaneoubakhane.movieservice.dto.Director.DirectorResponse;
import com.radouaneoubakhane.movieservice.dto.Director.MovieResponse;
import com.radouaneoubakhane.movieservice.service.DirectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/directors")
public class DirectorController {

    private final DirectorService directorService;

    // CRUD operations
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<DirectorResponse> getDirectors() {
        return directorService.getDirectors();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DirectorResponse getDirector(@PathVariable Long id) {
        return directorService.getDirector(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public DirectorResponse createDirector(@RequestBody DirectorRequest directorRequest) {
        return directorService.createDirector(directorRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DirectorResponse updateDirector(@PathVariable Long id ,@RequestBody DirectorRequest directorRequest) {
        return directorService.updateDirector(id, directorRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDirector(@PathVariable Long id ) {
        directorService.deleteDirector(id);
    }

    // Other operations

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<DirectorResponse> searchDirectors(@RequestParam String name) {
        return directorService.searchDirectors(name);
    }

    @GetMapping("/{directorId}/movies")
    @ResponseStatus(HttpStatus.OK)
    public List<MovieResponse> getDirectorMovies(@PathVariable Long directorId) {
        return directorService.getDirectorMovies(directorId);
    }

    @PutMapping("/{directorId}/movies/{movieId}")
    @ResponseStatus(HttpStatus.OK)
    public void addDirectorMovie(@PathVariable Long directorId, @PathVariable Long movieId) {
        directorService.addDirectorMovie(directorId, movieId);
    }

    @DeleteMapping("/{directorId}/movies/{movieId}")
    @ResponseStatus(HttpStatus.OK)
    public void removeDirectorMovie(@PathVariable Long directorId, @PathVariable Long movieId) {
        directorService.removeDirectorMovie(directorId, movieId);
    }

    // Endpoints for the user-service
    @GetMapping("/ids")
    @ResponseStatus(HttpStatus.OK)
    public List<DirectorResponse> getDirectorsByIds(@RequestParam List<Long> id) {
        return directorService.getDirectorsByIds(id);
    }
}
