package com.radouaneoubakhane.userservice.controller;


import com.radouaneoubakhane.userservice.dto.actor.FavoriteActorResponse;
import com.radouaneoubakhane.userservice.service.FavoriteActorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/favorite/actor")
public class FavoriteActorController {

    private final FavoriteActorService favoriteActorService ;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<FavoriteActorResponse> getMyFavoriteActors() {
        return favoriteActorService.getMyFavoriteActors();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FavoriteActorResponse getMyFavoriteActor(@PathVariable Long id) {
        return favoriteActorService.getMyFavoriteActor(id);
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addMyFavoriteActor(@PathVariable Long id) {
        favoriteActorService.addMyFavoriteActor(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMyFavoriteActor(@PathVariable Long id) {
        favoriteActorService.deleteMyFavoriteActor(id);
    }
}
