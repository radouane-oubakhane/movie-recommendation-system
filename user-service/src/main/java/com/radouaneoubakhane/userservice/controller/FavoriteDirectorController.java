package com.radouaneoubakhane.userservice.controller;

import com.radouaneoubakhane.userservice.dto.director.FavoriteDirectorResponse;

import java.util.List;

public interface FavoriteDirectorController {
    List<FavoriteDirectorResponse> getMyFavoriteDirectors();


    FavoriteDirectorResponse getMyFavoriteDirector(Long id);

    void addMyFavoriteDirector(Long id);


    void deleteMyFavoriteDirector(Long id);
}
