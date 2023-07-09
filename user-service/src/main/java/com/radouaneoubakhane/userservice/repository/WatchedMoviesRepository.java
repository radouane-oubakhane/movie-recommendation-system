package com.radouaneoubakhane.userservice.repository;

import com.radouaneoubakhane.userservice.entity.WatchedMovies;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WatchedMoviesRepository extends JpaRepository<WatchedMovies, Long> {
}
