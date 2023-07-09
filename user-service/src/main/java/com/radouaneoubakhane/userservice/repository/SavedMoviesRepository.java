package com.radouaneoubakhane.userservice.repository;

import com.radouaneoubakhane.userservice.entity.SavedMovies;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavedMoviesRepository extends JpaRepository<SavedMovies, Long> {
}
