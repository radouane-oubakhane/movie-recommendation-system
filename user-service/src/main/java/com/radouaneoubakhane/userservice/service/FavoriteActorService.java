package com.radouaneoubakhane.userservice.service;

import com.radouaneoubakhane.userservice.dto.actor.ActorResponse;
import com.radouaneoubakhane.userservice.entity.FavoriteActor;
import com.radouaneoubakhane.userservice.entity.User;
import com.radouaneoubakhane.userservice.exception.actor.ActorNotFoundException;
import com.radouaneoubakhane.userservice.repository.FavoriteActorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class FavoriteActorService {

    private final FavoriteActorRepository favoriteActorRepository;
    private final WebClient.Builder webClientBuilder;

    public List<ActorResponse> getMyFavoriteActors() {
        log.info("Getting my favorite actors");

        List<FavoriteActor > favoriteActors = favoriteActorRepository.findAllByUserId(1L);

        // Call the movie-service to get the favorite actors by ids and return them as a list
        // http://movie-service/api/v1/actor/ids?id=1&id=2&id=3
        return webClientBuilder.build()
                .get()
                .uri(
                        "http://movie-service/api/v1/actor/ids",
                                uriBuilder -> uriBuilder
                                .queryParam("id", favoriteActors.stream()
                                        .map(FavoriteActor::getActorId)
                                        .toList())
                                .build()
                        )
                .retrieve()
                .bodyToFlux(ActorResponse.class)
                .collectList()
                .block();

    }

    public ActorResponse getMyFavoriteActor(Long id) {
        log.info("Getting my favorite actor with id {}", id);

        FavoriteActor favoriteActor = favoriteActorRepository.findById(id)
                .orElseThrow(() -> new ActorNotFoundException("Favorite actor not found"));

        if (favoriteActor.getUser().getId() != 1L) {
            throw new RuntimeException("Favorite actor not found");
        }

        // Call the movie-service to get the favorite actor by id and return it
        // http://movie-service/api/v1/actor/{id}

        return webClientBuilder.build()
                .get()
                .uri(
                        "http://movie-service/api/v1/actor/{id}",
                        uriBuilder -> uriBuilder
                                .build(id)
                )
                .retrieve()
                .bodyToMono(ActorResponse.class)
                .block();
    }

    public void addMyFavoriteActor(Long id) {
        log.info("Adding my favorite actor with id {}", id);

        if (favoriteActorRepository.existsByUserIdAndActorId(1L, id)) {
            throw new RuntimeException("Favorite actor already exists");
        }

        // Call the movie-service to validate the actor id if it exists
        // http://movie-service/api/v1/actor/{id}
        ActorResponse actorResponse = webClientBuilder.build()
                .get()
                .uri(
                        "http://movie-service/api/v1/actor/{id}",
                        uriBuilder -> uriBuilder
                                .build(id)
                )
                .retrieve()
                .bodyToMono(ActorResponse.class)
                .block();

        if (actorResponse == null) {
            throw new ActorNotFoundException("Favorite actor not found");
        }

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
                .orElseThrow(() -> new ActorNotFoundException("Favorite actor not found"));

        if (favoriteActor.getUser().getId() != 1L) {
            throw new ActorNotFoundException("Favorite actor not found");
        }

        favoriteActorRepository.delete(favoriteActor);
    }
}
