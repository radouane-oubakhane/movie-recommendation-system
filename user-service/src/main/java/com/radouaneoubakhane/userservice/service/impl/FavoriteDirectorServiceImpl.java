package com.radouaneoubakhane.userservice.service.impl;


import com.radouaneoubakhane.userservice.dto.director.DirectorResponse;
import com.radouaneoubakhane.userservice.dto.director.FavoriteDirectorResponse;
import com.radouaneoubakhane.userservice.entity.FavoriteDirector;
import com.radouaneoubakhane.userservice.entity.User;
import com.radouaneoubakhane.userservice.exception.director.DirectorNotFoundException;
import com.radouaneoubakhane.userservice.repository.FavoriteDirectorRepository;
import com.radouaneoubakhane.userservice.service.FavoriteDirectorService;
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
public class FavoriteDirectorServiceImpl implements FavoriteDirectorService {

    private final FavoriteDirectorRepository favoriteDirectorRepository;
    private final WebClient.Builder webClientBuilder;



    public List<FavoriteDirectorResponse> getMyFavoriteDirectors() {
        log.info("getMyFavoriteDirectors");

        List<FavoriteDirector> favoriteDirectors = favoriteDirectorRepository.findAllByUserId(1L);

        // Call the movie-service to get the favorite directors
        // http://movie-service/api/v1/director/ids?id=id
        List<DirectorResponse> result = webClientBuilder.build()
                .get()
                .uri(
                        "lb://movie-service/api/v1/directors/ids",
                                uriBuilder -> uriBuilder
                                .queryParam("id", favoriteDirectors.stream()
                                        .map(FavoriteDirector::getDirectorId)
                                        .toList())
                                .build()
                        )
                .retrieve()
                .bodyToFlux(DirectorResponse.class)
                .collectList()
                .block();

        return mapToFavoriteDirectorResponse(favoriteDirectors, result);
    }

    private List<FavoriteDirectorResponse> mapToFavoriteDirectorResponse(List<FavoriteDirector> favoriteDirectors, List<DirectorResponse> result) {
        return favoriteDirectors.stream()
                .map(favoriteDirector -> {
                    DirectorResponse directorResponse = result.stream()
                            .filter(director -> director.getId().equals(favoriteDirector.getDirectorId()))
                            .findFirst()
                            .orElseThrow(() -> new DirectorNotFoundException("Favorite director not found"));

                    return FavoriteDirectorResponse.builder()
                            .id(favoriteDirector.getId())
                            .director(directorResponse)
                            .build();
                })
                .toList();
    }

    public FavoriteDirectorResponse getMyFavoriteDirector(Long id) {
        log.info("getMyFavoriteDirector with id {}", id);

            FavoriteDirector favoriteDirector = favoriteDirectorRepository.findById(id)
                    .orElseThrow(() -> new DirectorNotFoundException("Favorite director not found"));

            if (favoriteDirector.getUser().getId() != 1L) {
                throw new RuntimeException("Favorite director not found");
            }

        // Call the movie-service to get the favorite director
        // http://movie-service/api/v1/director/{id}
        DirectorResponse result = webClientBuilder.build()
                .get()
                .uri(
                        "lb://movie-service/api/v1/directors/{id}",
                                uriBuilder -> uriBuilder
                                .build(favoriteDirector.getDirectorId())
                        )
                .retrieve()
                .bodyToMono(DirectorResponse.class)
                .block();

        return FavoriteDirectorResponse.builder()
                .id(favoriteDirector.getId())
                .director(result)
                .build();

    }

    public void addMyFavoriteDirector(Long id) {
        log.info("addMyFavoriteDirector with id {}", id);

        if (favoriteDirectorRepository.existsByUserIdAndDirectorId(1L, id)) {
            throw new RuntimeException("Favorite director already exists");
        }

        // Call the movie-service to get the favorite director
        // http://movie-service/api/v1/director/{id}
        DirectorResponse result = webClientBuilder.build()
                .get()
                .uri(
                        "lb://movie-service/api/v1/directors/{id}",
                                uriBuilder -> uriBuilder
                                .build(id)
                        )
                .retrieve()
                .bodyToMono(DirectorResponse.class)
                .block();

        if (result == null) {
            throw new DirectorNotFoundException("Favorite director not found");
        }

        User user = User.builder()
                .id(1L)
                .build();

        FavoriteDirector favoriteDirector = FavoriteDirector.builder()
                .id(1L)
                .directorId(id)
                .user(user)
                .build();

        favoriteDirectorRepository.save(favoriteDirector);
    }

    public void deleteMyFavoriteDirector(Long id) {
        log.info("deleteMyFavoriteDirector with id {}", id);

        FavoriteDirector favoriteDirector = favoriteDirectorRepository.findById(id)
                .orElseThrow(() -> new DirectorNotFoundException("Favorite director not found"));

        if (favoriteDirector.getUser().getId() != 1L) {
            throw new RuntimeException("Favorite director not found");
        }

        favoriteDirectorRepository.delete(favoriteDirector);
    }
}
