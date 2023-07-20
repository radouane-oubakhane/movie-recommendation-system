package com.radouaneoubakhane.userservice.repository;

import com.radouaneoubakhane.userservice.entity.WatchedMovie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WatchedMovieRepository extends JpaRepository<WatchedMovie, Long> {
}
