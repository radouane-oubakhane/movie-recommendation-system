package com.radouaneoubakhane.userservice.controller.impl;


import com.radouaneoubakhane.userservice.dto.actor.FavoriteActorResponse;
import com.radouaneoubakhane.userservice.service.FavoriteActorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/favorite/actors")
public class FavoriteActorControllerImpl implements com.radouaneoubakhane.userservice.controller.FavoriteActorController {

    private final FavoriteActorService favoriteActorService ;

    @Override
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<FavoriteActorResponse> getMyFavoriteActors() {
        return favoriteActorService.getMyFavoriteActors();
    }

    @Override
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FavoriteActorResponse getMyFavoriteActor(@PathVariable Long id) {
        return favoriteActorService.getMyFavoriteActor(id);
    }

    @Override
    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addMyFavoriteActor(@PathVariable Long id) {
        favoriteActorService.addMyFavoriteActor(id);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMyFavoriteActor(@PathVariable Long id) {
        favoriteActorService.deleteMyFavoriteActor(id);
    }
}
