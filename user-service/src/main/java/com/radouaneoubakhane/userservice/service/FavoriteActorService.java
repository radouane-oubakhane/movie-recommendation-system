package com.radouaneoubakhane.userservice.service;

import com.radouaneoubakhane.userservice.dto.actor.FavoriteActorResponse;

import java.util.List;

public interface FavoriteActorService {
    List<FavoriteActorResponse> getMyFavoriteActors();

    FavoriteActorResponse getMyFavoriteActor(Long id);

    void addMyFavoriteActor(Long id);

    void deleteMyFavoriteActor(Long id);
}
