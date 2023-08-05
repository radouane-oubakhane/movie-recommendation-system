package com.radouaneoubakhane.movieservice.controller.impl;


import com.radouaneoubakhane.movieservice.controller.DirectorController;
import com.radouaneoubakhane.movieservice.dto.director.DirectorRequest;
import com.radouaneoubakhane.movieservice.dto.director.DirectorResponse;
import com.radouaneoubakhane.movieservice.dto.director.MovieResponse;
import com.radouaneoubakhane.movieservice.service.DirectorService;
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
@RequestMapping("/api/v1/directors")
public class DirectorControllerImpl implements DirectorController {

    private final DirectorService directorService;

    // CRUD operations

    @Override
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<DirectorResponse> getDirectorsWithPaginationAndSorting(@RequestParam(defaultValue = "0") Integer pageNo,
                                                                       @RequestParam(defaultValue = "10") Integer pageSize,
                                                                       @RequestParam(defaultValue = "firstName, asc") String[] sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, getSortOrder(sortBy));
        return directorService.getDirectors(pageable);
    }

    @Override
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<DirectorResponse> getDirectors() {
        return directorService.getDirectors();
    }

    @Override
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DirectorResponse getDirector(@PathVariable Long id) {
        return directorService.getDirector(id);
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public DirectorResponse createDirector(@RequestBody DirectorRequest directorRequest) {
        return directorService.createDirector(directorRequest);
    }

    @Override
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DirectorResponse updateDirector(@PathVariable Long id, @RequestBody DirectorRequest directorRequest) {
        return directorService.updateDirector(id, directorRequest);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDirector(@PathVariable Long id) {
        directorService.deleteDirector(id);
    }

    // Other operations

    @Override
    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<DirectorResponse> searchDirectors(@RequestParam String name) {
        return directorService.searchDirectors(name);
    }

    @Override
    @GetMapping("/{directorId}/movies")
    @ResponseStatus(HttpStatus.OK)
    public List<MovieResponse> getDirectorMovies(@PathVariable Long directorId) {
        return directorService.getDirectorMovies(directorId);
    }

    @Override
    @PutMapping("/{directorId}/movies/{movieId}")
    @ResponseStatus(HttpStatus.OK)
    public void addDirectorMovie(@PathVariable Long directorId, @PathVariable Long movieId) {
        directorService.addDirectorMovie(directorId, movieId);
    }

    @Override
    @DeleteMapping("/{directorId}/movies/{movieId}")
    @ResponseStatus(HttpStatus.OK)
    public void removeDirectorMovie(@PathVariable Long directorId, @PathVariable Long movieId) {
        directorService.removeDirectorMovie(directorId, movieId);
    }

    // Endpoints for the user-service
    @Override
    @GetMapping("/ids")
    @ResponseStatus(HttpStatus.OK)
    public List<DirectorResponse> getDirectorsByIds(@RequestParam List<Long> id) {
        return directorService.getDirectorsByIds(id);
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
