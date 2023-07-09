package com.radouaneoubakhane.userservice.repository;


import com.radouaneoubakhane.userservice.entity.FavoriteActors;
import com.radouaneoubakhane.userservice.entity.FavoriteDirectors;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteDirectorsRepository extends JpaRepository<FavoriteDirectors, Long> {
}
