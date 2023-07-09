package com.radouaneoubakhane.userservice.util;


import com.radouaneoubakhane.userservice.repository.*;
import com.radouaneoubakhane.userservice.util.DataGenerators.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final FavoriteActorsRepository favoriteActorsRepository;
    private final FavoriteDirectorsRepository favoriteDirectorsRepository;
    private final FavoriteMoviesRepository favoriteMoviesRepository;
    private final SavedMoviesRepository savedMoviesRepository;
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final WatchedMoviesRepository watchedMoviesRepository;
    private final WatchlistRepository watchlistRepository;


    @Override
    public void run(String... args) throws Exception {
        userRepository.save(UserDataGenerator.generateUser());
        profileRepository.save(ProfileDataGenerator.generateProfile());
        favoriteActorsRepository.save(FavoriteActorsDataGenerator.getInstance());
        favoriteDirectorsRepository.save(FavoriteDirectorsDataGenerator.generateFavoriteDirectors());
        favoriteMoviesRepository.save(FavoriteMoviesDataGenerator.generateFavoriteMovies());
        savedMoviesRepository.save(SavedMoviesDataGenerator.generateSavedMovies());
        watchedMoviesRepository.save(WatchedMoviesDataGenerator.generateWatchedMovies());
        watchlistRepository.save(WatchlistDataGenerator.generateWatchlist());
    }
}
