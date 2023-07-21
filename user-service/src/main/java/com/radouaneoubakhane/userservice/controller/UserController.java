package com.radouaneoubakhane.userservice.controller;


import com.radouaneoubakhane.userservice.dto.user.UserRequest;
import com.radouaneoubakhane.userservice.dto.user.UserResponse;
import com.radouaneoubakhane.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    // My user endpoints

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getMyUser() {
        return userService.getMyUser();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public UserResponse updateMyUser(@RequestBody UserRequest userRequest) {
        return userService.updateMyUser(userRequest);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createMyUser(@RequestBody UserRequest userRequest) {
        return userService.createMyUser(userRequest);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMyUser() {
        userService.deleteMyUser();
    }

    // Admin operations

//    @GetMapping
//    @ResponseStatus(HttpStatus.OK)
//    public List<UserResponse> getUsers() {
//        return userService.getUsers();
//    }
//
//    @GetMapping("/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public UserResponse getUser(@PathVariable Long id) {
//        return userService.getUser(id);
//    }
//
//    @PutMapping("/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public UserResponse updateUser(@PathVariable Long id, @RequestBody UserRequest userRequest) {
//        return userService.updateUser(id, userRequest);
//    }
//
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public UserResponse createUser(@RequestBody UserRequest userRequest) {
//        return userService.createUser(userRequest);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteUser(@PathVariable Long id) {
//        userService.deleteUser(id);
//    }
//
//    // Profile endpoints
//
//    @GetMapping("/me/profile")
//    public ProfileResponse getMyProfile() {
//        return userService.getMyProfile();
//    }
//
//    @PutMapping("/me/profile")
//    public ProfileResponse updateMyProfile(@RequestBody ProfileRequest profileRequest) {
//        return userService.updateMyProfile(profileRequest);
//    }
//
//    @PostMapping("/me/profile")
//    public ProfileResponse createMyProfile(@RequestBody ProfileRequest profileRequest) {
//        return userService.createMyProfile(profileRequest);
//    }
//
//    @DeleteMapping("/me/profile")
//        public void deleteMyProfile() {
//            userService.deleteMyProfile();
//        }
//
//    // Favorites Actors endpoints
//
//    @GetMapping("/me/favorites/actors")
//    public List<ActorResponse> getMyFavoritesActors() {
//        return userService.getMyFavoritesActors();
//    }
//
//    @GetMapping("/me/favorites/actors/{id}")
//    public ActorResponse getMyFavoriteActor(@PathVariable Long id) {
//        return userService.getMyFavoriteActor(id);
//    }
//
//    @PostMapping("/me/favorites/actors/{id}")
//    public void createMyFavoriteActor(@PathVariable Long id) {
//        userService.createMyFavoriteActor(id);
//    }
//
//    @DeleteMapping("/me/favorites/actors/{id}")
//    public void deleteMyFavoriteActor(@PathVariable Long id) {
//        userService.deleteMyFavoriteActor(id);
//    }
//
//    // Favorites Directors endpoints
//
//    @GetMapping("/me/favorites/directors")
//    public List<DirectorResponse> getMyFavoritesDirectors() {
//        return userService.getMyFavoritesDirectors();
//    }
//
//    @GetMapping("/me/favorites/directors/{id}")
//    public DirectorResponse getMyFavoriteDirector(@PathVariable Long id) {
//        return userService.getMyFavoriteDirector(id);
//    }
//
//    @PostMapping("/me/favorites/directors/{id}")
//    public void createMyFavoriteDirector(@PathVariable Long id) {
//        userService.createMyFavoriteDirector(id);
//    }
//
//    @DeleteMapping("/me/favorites/directors/{id}")
//    public void deleteMyFavoriteDirector(@PathVariable Long id) {
//        userService.deleteMyFavoriteDirector(id);
//    }
//
//    // Favorites Movies endpoints
//
//    @GetMapping("/me/favorites/movies")
//    public List<MovieResponse> getMyFavoritesMovies() {
//        return userService.getMyFavoritesMovies();
//    }
//
//    @GetMapping("/me/favorites/movies/{id}")
//    public MovieResponse getMyFavoriteMovie(@PathVariable Long id) {
//        return userService.getMyFavoriteMovie(id);
//    }
//
//    @PostMapping("/me/favorites/movies/{id}")
//    public void createMyFavoriteMovie(@PathVariable Long id) {
//        userService.createMyFavoriteMovie(id);
//    }
//
//    @DeleteMapping("/me/favorites/movies/{id}")
//    public void deleteMyFavoriteMovie(@PathVariable Long id) {
//        userService.deleteMyFavoriteMovie(id);
//    }
//
//    // Saved Movies endpoints
//
//    @GetMapping("/me/saved/movies")
//    public List<MovieResponse> getMySavedMovies() {
//        return userService.getMySavedMovies();
//    }
//
//    @GetMapping("/me/saved/movies/{id}")
//    public MovieResponse getMySavedMovie(@PathVariable Long id) {
//        return userService.getMySavedMovie(id);
//    }
//
//    @PostMapping("/me/saved/movies/{id}")
//    public void createMySavedMovie(@PathVariable Long id) {
//        userService.createMySavedMovie(id);
//    }
//
//    @DeleteMapping("/me/saved/movies/{id}")
//    public void deleteMySavedMovie(@PathVariable Long id) {
//        userService.deleteMySavedMovie(id);
//    }
//
//    // Watched Movies endpoints
//
//    @GetMapping("/me/watched/movies")
//    public List<MovieResponse> getMyWatchedMovies() {
//        return userService.getMyWatchedMovies();
//    }
//
//    @GetMapping("/me/watched/movies/{id}")
//    public MovieResponse getMyWatchedMovie(@PathVariable Long id) {
//        return userService.getMyWatchedMovie(id);
//    }
//
//    @PostMapping("/me/watched/movies/{id}")
//    public void createMyWatchedMovie(@PathVariable Long id) {
//        userService.createMyWatchedMovie(id);
//    }
//
//    @DeleteMapping("/me/watched/movies/{id}")
//    public void deleteMyWatchedMovie(@PathVariable Long id) {
//        userService.deleteMyWatchedMovie(id);
//    }
//
//    // Watchlist Movies endpoints
//
//    @GetMapping("/me/watchlist/movies")
//    public List<MovieResponse> getMyWatchlistMovies() {
//        return userService.getMyWatchlistMovies();
//    }
//
//    @GetMapping("/me/watchlist/movies/{id}")
//    public MovieResponse getMyWatchlistMovie(@PathVariable Long id) {
//        return userService.getMyWatchlistMovie(id);
//    }
//
//    @PostMapping("/me/watchlist/movies/{id}")
//    public void createMyWatchlistMovie(@PathVariable Long id) {
//        userService.createMyWatchlistMovie(id);
//    }
//
//    @DeleteMapping("/me/watchlist/movies/{id}")
//    public void deleteMyWatchlistMovie(@PathVariable Long id) {
//        userService.deleteMyWatchlistMovie(id);
//    }


}
