package com.radouaneoubakhane.userservice.service.impl;

import com.radouaneoubakhane.userservice.dto.actor.ActorResponse;
import com.radouaneoubakhane.userservice.dto.actor.FavoriteActorResponse;
import com.radouaneoubakhane.userservice.domain.FavoriteActor;
import com.radouaneoubakhane.userservice.domain.User;
import com.radouaneoubakhane.userservice.exception.actor.ActorNotFoundException;
import com.radouaneoubakhane.userservice.repository.FavoriteActorRepository;
import com.radouaneoubakhane.userservice.service.FavoriteActorService;
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
public class FavoriteActorServiceImpl implements FavoriteActorService {

    private final FavoriteActorRepository favoriteActorRepository;
    private final WebClient.Builder webClientBuilder;

    public List<FavoriteActorResponse> getMyFavoriteActors() {
        log.info("Getting my favorite actors");

        List<FavoriteActor> favoriteActors = favoriteActorRepository.findAllByUserId(1L);

        // Call the movie-service to get the favorite actors by ids and return them as a list
        // http://movie-service/api/v1/actors/ids?id=id
        List<ActorResponse> result = webClientBuilder.build()
                .get()
                .uri(
                        "lb://movie-service/api/v1/actors/ids",
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

        return mapToFavoriteActorResponse(favoriteActors, result);
    }

    private List<FavoriteActorResponse> mapToFavoriteActorResponse(List<FavoriteActor> favoriteActors, List<ActorResponse> result) {
        return favoriteActors.stream()
                .map(favoriteActor -> {
                    ActorResponse actorResponse = result.stream()
                            .filter(actor -> actor.getId().equals(favoriteActor.getActorId()))
                            .findFirst()
                            .orElseThrow(() -> new ActorNotFoundException("Favorite actor not found"));

                    return FavoriteActorResponse.builder()
                            .id(favoriteActor.getId())
                            .actor(actorResponse)
                            .build();
                })
                .toList();
    }

    public FavoriteActorResponse getMyFavoriteActor(Long id) {
        log.info("Getting my favorite actor with id {}", id);

        FavoriteActor favoriteActor = favoriteActorRepository.findById(id)
                .orElseThrow(() -> new ActorNotFoundException("Favorite actor not found"));

        if (favoriteActor.getUser().getId() != 1L) {
            throw new IllegalArgumentException("Favorite actor not found");
        }

        // Call the movie-service to get the favorite actor by id and return it
        // http://movie-service/api/v1/actors/{id}

        ActorResponse result = webClientBuilder.build()
                .get()
                .uri(
                        "lb://movie-service/api/v1/actors/{id}",
                        uriBuilder -> uriBuilder
                                .build(favoriteActor.getActorId())
                )
                .retrieve()
                .bodyToMono(ActorResponse.class)
                .block();

        return FavoriteActorResponse.builder()
                .id(favoriteActor.getId())
                .actor(result)
                .build();
    }

    public void addMyFavoriteActor(Long id) {
        log.info("Adding my favorite actor with id {}", id);

        if (favoriteActorRepository.existsByUserIdAndActorId(1L, id)) {
            throw new IllegalArgumentException("Favorite actor already exists");
        }

        // Call the movie-service to validate the actor id if it exists
        // http://movie-service/api/v1/actor/{id}
        ActorResponse actorResponse = webClientBuilder.build()
                .get()
                .uri(
                        "lb://movie-service/api/v1/actors/{id}",
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

