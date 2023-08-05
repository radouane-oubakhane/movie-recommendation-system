package com.radouaneoubakhane.userservice.controller.impl;


import com.radouaneoubakhane.userservice.dto.director.FavoriteDirectorResponse;
import com.radouaneoubakhane.userservice.service.FavoriteDirectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/favorite/directors")
public class FavoriteDirectorControllerImpl implements com.radouaneoubakhane.userservice.controller.FavoriteDirectorController {

    private final FavoriteDirectorService favoriteDirectorService;

    @Override
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<FavoriteDirectorResponse> getMyFavoriteDirectors() {
        return favoriteDirectorService.getMyFavoriteDirectors();
    }
    
    @Override
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FavoriteDirectorResponse getMyFavoriteDirector(@PathVariable Long id) {
        return favoriteDirectorService.getMyFavoriteDirector(id);
    }
    
    @Override
    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addMyFavoriteDirector(@PathVariable Long id) {
        favoriteDirectorService.addMyFavoriteDirector(id);
    }
    
    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMyFavoriteDirector(@PathVariable Long id) {
        favoriteDirectorService.deleteMyFavoriteDirector(id);
    }
}
