package com.radouaneoubakhane.movieservice.service;

import com.radouaneoubakhane.movieservice.dto.Director.DirectorRequest;
import com.radouaneoubakhane.movieservice.dto.Director.DirectorResponse;
import com.radouaneoubakhane.movieservice.dto.Director.MovieResponse;

import java.util.List;

public interface DirectorService {
    List<DirectorResponse> getDirectors();

    DirectorResponse getDirector(Long id);

    DirectorResponse createDirector(DirectorRequest directorRequest);

    DirectorResponse updateDirector(Long id, DirectorRequest directorRequest);

    void deleteDirector(Long id);

    List<DirectorResponse> searchDirectors(String name);

    List<MovieResponse> getDirectorMovies(Long directorId);

    void addDirectorMovie(Long directorId, Long movieId);

    void removeDirectorMovie(Long directorId, Long movieId);

    List<DirectorResponse> getDirectorsByIds(List<Long> id);
}
