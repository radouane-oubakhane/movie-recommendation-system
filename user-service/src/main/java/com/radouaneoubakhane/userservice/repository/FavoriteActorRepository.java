package com.radouaneoubakhane.userservice.repository;


import com.radouaneoubakhane.userservice.entity.FavoriteActor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteActorRepository extends JpaRepository<FavoriteActor, Long> {

    List<FavoriteActor> findAllByUserId(long l);

    FavoriteActor findByIdAndUserId(Long id, long l);

    boolean existsByUserIdAndActorId(long l, Long id);
}
