package com.radouaneoubakhane.userservice.service;


import com.radouaneoubakhane.userservice.dto.director.DirectorResponse;
import com.radouaneoubakhane.userservice.entity.FavoriteDirector;
import com.radouaneoubakhane.userservice.entity.User;
import com.radouaneoubakhane.userservice.exception.director.DirectorNotFoundException;
import com.radouaneoubakhane.userservice.repository.FavoriteDirectorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class FavoriteDirectorService {

    final FavoriteDirectorRepository favoriteDirectorRepository;


    public List<DirectorResponse> getMyFavoriteDirectors() {
        log.info("getMyFavoriteDirectors");

        List<FavoriteDirector> favoriteDirectors = favoriteDirectorRepository.findAllByUserId(1L);

        // Call the movie-service to get the favorite directors

        return favoriteDirectors.stream()
                        .map(this::mapFavoriteDirectorsToDirectorResponse)
                        .toList();
    }

    private DirectorResponse mapFavoriteDirectorsToDirectorResponse(FavoriteDirector favoriteDirector) {
        return DirectorResponse.builder()
                .id(favoriteDirector.getId())
                .directorId(favoriteDirector.getDirectorId())
                .build();
    }


    public DirectorResponse getMyFavoriteDirector(Long id) {
        log.info("getMyFavoriteDirector with id {}", id);

            FavoriteDirector favoriteDirector = favoriteDirectorRepository.findById(id)
                    .orElseThrow(() -> new DirectorNotFoundException("Favorite director not found"));

            if (favoriteDirector.getUserId() != 1L) {
                throw new RuntimeException("Favorite director not found");
            }

            // Call the movie-service to get the favorite director

            return mapFavoriteDirectorsToDirectorResponse(favoriteDirector);
    }

    public void addMyFavoriteDirector(Long id) {
        log.info("addMyFavoriteDirector with id {}", id);

        if (favoriteDirectorRepository.existsByUserIdAndDirectorId(1L, id)) {
            throw new RuntimeException("Favorite director already exists");
        }

        // Call the movie-service to get the favorite director

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

        if (favoriteDirector.getUserId() != 1L) {
            throw new RuntimeException("Favorite director not found");
        }

        favoriteDirectorRepository.delete(favoriteDirector);
    }
}
