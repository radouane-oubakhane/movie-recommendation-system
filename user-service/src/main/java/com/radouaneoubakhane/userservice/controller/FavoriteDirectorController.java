package com.radouaneoubakhane.userservice.controller;


import com.radouaneoubakhane.userservice.dto.director.DirectorResponse;
import com.radouaneoubakhane.userservice.service.FavoriteDirectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/favorite/directors")
public class FavoriteDirectorController {

    private final FavoriteDirectorService favoriteDirectorService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<DirectorResponse> getMyFavoriteDirectors() {
        return favoriteDirectorService.getMyFavoriteDirectors();
    }
    
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DirectorResponse getMyFavoriteDirector(@PathVariable Long id) {
        return favoriteDirectorService.getMyFavoriteDirector(id);
    }
    
    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addMyFavoriteDirector(@PathVariable Long id) {
        favoriteDirectorService.addMyFavoriteDirector(id);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMyFavoriteDirector(@PathVariable Long id) {
        favoriteDirectorService.deleteMyFavoriteDirector(id);
    }
}
