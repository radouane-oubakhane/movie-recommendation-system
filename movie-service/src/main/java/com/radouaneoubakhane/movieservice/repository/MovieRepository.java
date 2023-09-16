package com.radouaneoubakhane.movieservice.repository;

import com.radouaneoubakhane.movieservice.model.Movie;
import com.radouaneoubakhane.movieservice.enums.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByTitleContainingIgnoreCase(String title);

    List<Movie> findByGenre(Genre genre);
}
