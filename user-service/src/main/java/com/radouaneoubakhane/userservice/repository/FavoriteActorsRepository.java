package com.radouaneoubakhane.userservice.repository;


import com.radouaneoubakhane.userservice.entity.FavoriteActors;
import com.radouaneoubakhane.userservice.entity.FavoriteMovies;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteActorsRepository extends JpaRepository<FavoriteActors, Long> {
}
