package com.radouaneoubakhane.movieservice.service;

import com.radouaneoubakhane.movieservice.dto.director.DirectorRequest;
import com.radouaneoubakhane.movieservice.dto.director.DirectorResponse;
import com.radouaneoubakhane.movieservice.dto.director.MovieResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DirectorService {
    List<DirectorResponse> getDirectors();

    Page<DirectorResponse> getDirectors(Pageable pageable);

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
