package com.radouaneoubakhane.userservice.repository;


import com.radouaneoubakhane.userservice.entity.FavoriteActor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteActorRepository extends JpaRepository<FavoriteActor, Long> {

    List<FavoriteActor> findAllByUserId(long l);

    boolean existsByUserIdAndActorId(long UserId, Long actorId);
}
