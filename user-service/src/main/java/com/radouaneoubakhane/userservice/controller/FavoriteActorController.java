package com.radouaneoubakhane.userservice.controller;

import com.radouaneoubakhane.userservice.dto.actor.FavoriteActorResponse;

import java.util.List;

public interface FavoriteActorController {

    List<FavoriteActorResponse> getMyFavoriteActors();


    FavoriteActorResponse getMyFavoriteActor(Long id);


    void addMyFavoriteActor(Long id);


    void deleteMyFavoriteActor(Long id);
}
