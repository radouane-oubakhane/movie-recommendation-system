package com.radouaneoubakhane.userservice.repository;


import com.radouaneoubakhane.userservice.domain.FavoriteMovie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteMovieRepository extends JpaRepository<FavoriteMovie, Long> {
    List<FavoriteMovie> findAllByUserId(long l);

    boolean existsByUserIdAndMovieId(long l, Long id);
}
