package com.radouaneoubakhane.movieservice.controller;

import com.radouaneoubakhane.movieservice.dto.director.DirectorRequest;
import com.radouaneoubakhane.movieservice.dto.director.DirectorResponse;
import com.radouaneoubakhane.movieservice.dto.director.MovieResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DirectorController {

    Page<DirectorResponse> getDirectorsWithPaginationAndSorting(Integer pageNo, Integer pageSize, String[] sortBy);


    List<DirectorResponse> getDirectors();


    DirectorResponse getDirector(Long id);

    DirectorResponse createDirector(DirectorRequest directorRequest);


    DirectorResponse updateDirector(Long id, DirectorRequest directorRequest);

    void deleteDirector(Long id);

    List<DirectorResponse> searchDirectors(String name);

    List<MovieResponse> getDirectorMovies(Long directorId);

    void addDirectorMovie(Long directorId, Long movieId);

    void removeDirectorMovie(Long directorId, Long movieId);

    // Endpoints for the user-service
    List<DirectorResponse> getDirectorsByIds(List<Long> id);
}
