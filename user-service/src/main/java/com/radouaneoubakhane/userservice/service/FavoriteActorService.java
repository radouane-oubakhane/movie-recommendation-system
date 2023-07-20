package com.radouaneoubakhane.userservice.service;

import com.radouaneoubakhane.userservice.dto.actor.ActorResponse;
import com.radouaneoubakhane.userservice.entity.FavoriteActor;
import com.radouaneoubakhane.userservice.entity.User;
import com.radouaneoubakhane.userservice.repository.FavoriteActorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class FavoriteActorService {

    private final FavoriteActorRepository favoriteActorRepository;
    private final UserService userService;

    public List<ActorResponse> getMyFavoriteActors() {
        log.info("Getting my favorite actors");

        List<FavoriteActor > favoriteActors = favoriteActorRepository.findAllByUserId(1L);
        // Call the movie-service to get the favorite actors

        return favoriteActors.stream()
                .map(this::mapFavoriteActorsToActorResponse)
                .toList();
    }

    private ActorResponse mapFavoriteActorsToActorResponse(FavoriteActor favoriteActors) {
        return ActorResponse.builder()
                .id(1L)
                .actorId(1L)
                .build();
    }


    public ActorResponse getMyFavoriteActor(Long id) {
        log.info("Getting my favorite actor with id {}", id);

            FavoriteActor favoriteActor = favoriteActorRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Favorite actor not found"));

            if (favoriteActor.getUserId() != 1L) {
                throw new RuntimeException("Favorite actor not found");
            }

            // Call the movie-service to get the favorite actor

            return mapFavoriteActorsToActorResponse(favoriteActor);
    }

    public void addMyFavoriteActor(Long id) {
        log.info("Adding my favorite actor with id {}", id);

        // Call the movie-service to validate the actor id if it exists

        User user = User.builder()
                .id(1L)
                .build();

        FavoriteActor favoriteActor = FavoriteActor.builder()
                .actorId(id)
                .user(user)
                .build();

        favoriteActorRepository.save(favoriteActor);
    }

    public void deleteMyFavoriteActor(Long id) {
        log.info("Deleting my favorite actor with id {}", id);

        FavoriteActor favoriteActor = favoriteActorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Favorite actor not found"));

        if (favoriteActor.getUserId() != 1L) {
            throw new RuntimeException("Favorite actor not found");
        }

        favoriteActorRepository.delete(favoriteActor);
    }
}
