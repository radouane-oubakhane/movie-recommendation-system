package com.radouaneoubakhane.movieservice.repository;

import com.radouaneoubakhane.movieservice.domain.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findByMovieId(Long movieId);
}
