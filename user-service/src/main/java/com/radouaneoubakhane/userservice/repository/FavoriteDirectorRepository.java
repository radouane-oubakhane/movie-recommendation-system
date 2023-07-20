package com.radouaneoubakhane.userservice.repository;


import com.radouaneoubakhane.userservice.entity.FavoriteDirector;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteDirectorRepository extends JpaRepository<FavoriteDirector, Long> {
}
