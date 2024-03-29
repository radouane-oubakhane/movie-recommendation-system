package com.radouaneoubakhane.userservice.repository;


import com.radouaneoubakhane.userservice.model.FavoriteDirector;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteDirectorRepository extends JpaRepository<FavoriteDirector, Long> {
    List<FavoriteDirector> findAllByUserId(long l);

    boolean existsByUserIdAndDirectorId(long l, Long id);
}
