package com.radouaneoubakhane.userservice.repository;

import com.radouaneoubakhane.userservice.entity.SavedMovie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavedMovieRepository extends JpaRepository<SavedMovie, Long> {
}
