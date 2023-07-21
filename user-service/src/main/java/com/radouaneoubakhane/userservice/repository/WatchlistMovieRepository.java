package com.radouaneoubakhane.userservice.repository;

import com.radouaneoubakhane.userservice.entity.WatchlistMovie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WatchlistMovieRepository extends JpaRepository<WatchlistMovie, Long> {
    List<WatchlistMovie> findAllByUserId(long l);
}
