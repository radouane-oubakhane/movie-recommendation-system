package com.radouaneoubakhane.userservice.repository;

import com.radouaneoubakhane.userservice.model.WatchedMovie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WatchedMovieRepository extends JpaRepository<WatchedMovie, Long> {
    List<WatchedMovie> findAllByUserId(long l);

    boolean existsByMovieIdAndUserId(Long id, long l);
}
