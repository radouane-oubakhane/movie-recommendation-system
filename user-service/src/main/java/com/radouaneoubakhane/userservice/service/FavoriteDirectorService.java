package com.radouaneoubakhane.userservice.service;

import com.radouaneoubakhane.userservice.dto.director.FavoriteDirectorResponse;

import java.util.List;

public interface FavoriteDirectorService {
    List<FavoriteDirectorResponse> getMyFavoriteDirectors();

    FavoriteDirectorResponse getMyFavoriteDirector(Long id);

    void addMyFavoriteDirector(Long id);

    void deleteMyFavoriteDirector(Long id);
}
