package com.radouaneoubakhane.userservice.repository;

import com.radouaneoubakhane.userservice.entity.SavedMovie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SavedMovieRepository extends JpaRepository<SavedMovie, Long> {
    List<SavedMovie> findAllByUserId(long l);

    boolean existsByMovieIdAndUserId(Long id, long l);
}
