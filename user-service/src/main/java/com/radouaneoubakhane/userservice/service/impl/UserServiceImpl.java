package com.radouaneoubakhane.userservice.service.impl;

import com.radouaneoubakhane.userservice.dto.user.*;
import com.radouaneoubakhane.userservice.exception.user.UserNotFoundException;
import com.radouaneoubakhane.userservice.mapper.profile.ProfileResponseMapper;
import com.radouaneoubakhane.userservice.mapper.user.UserProfileResponseMapper;
import com.radouaneoubakhane.userservice.model.Profile;
import com.radouaneoubakhane.userservice.model.User;
import com.radouaneoubakhane.userservice.repository.UserRepository;
import com.radouaneoubakhane.userservice.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ProfileServiceImpl profileService;
    private final ProfileResponseMapper profileResponseMapper;
    private final UserProfileResponseMapper userProfileResponseMapper;



    // My user endpoints
    public UserResponse getMyUser() {
        log.info("Fetching my user");

        User user = userRepository.findById(1L).orElseThrow(
                () -> new UserNotFoundException("User not found")
        );

        return mapUserToUserResponse(user);
    }

    private UserResponse mapUserToUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .profile(mapProfileToProfileResponse(user.getProfile()))
                .build();
    }

    private ProfileResponse mapProfileToProfileResponse(Profile profile) {
        return ProfileResponse.builder()
                        .id(profile.getId())
                        .firstName(profile.getFirstName())
                        .lastName(profile.getLastName())
                        .profilePicture(profile.getProfilePicture())
                        .birthDate(profile.getBirthDate())
                        .birthPlace(profile.getBirthPlace())
                        .bio(profile.getBio())
                        .preferences(profile.getPreferences())
                        .build();
    }

    public UserResponse updateMyUser(UserRequest userRequest) {
        log.info("Updating my user");

        User user = userRepository.findById(1L).orElseThrow(
                () -> new UserNotFoundException("User not found")
        );

        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());

        return mapUserToUserResponse(user);
    }


    public UserResponse createMyUser(UserRequest userRequest) {
        log.info("Creating my user");

        User user = User.builder()
                .username(userRequest.getUsername())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .build();

        User savedUser = userRepository.save(user);

        return mapUserToUserResponse(savedUser);
    }

    public void deleteMyUser() {
        log.info("Deleting my user");

        User user = userRepository.findById(1L).orElseThrow(
                () -> new UserNotFoundException("User not found")
        );

        profileService.deleteProfile(user.getProfile().getId());

        userRepository.delete(user);
    }


    // Admin endpoints
    public List<UserResponse> getUsers() {
        log.info("Fetching all users");

        List<User> users = userRepository.findAll();

        return users.stream().map(this::mapUserToUserResponse).toList();
    }

    public UserResponse getUser(Long id) {
        log.info("Fetching user with id {}", id);

        User user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User not found")
        );

        return mapUserToUserResponse(user);
    }


    public UserResponse updateUser(Long id, UserRequest userRequest) {
        log.info("Updating user with id {}", id);

        User user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User not found")
        );

        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());

        return mapUserToUserResponse(user);
    }

    public UserResponse createUser(UserRequest userRequest) {
        log.info("Creating user");

        if (userRepository.existsByUsername(userRequest.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        User user = User.builder()
                .username(userRequest.getUsername())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .build();

        User savedUser = userRepository.save(user);

        return mapUserToUserResponse(savedUser);
    }

    public void deleteUser(Long id) {
        log.info("Deleting user with id {}", id);

        User user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User not found")
        );

        profileService.deleteProfile(user.getProfile().getId());

        userRepository.delete(user);
    }
    

    // Profile endpoints

    public ProfileResponse getMyProfile() {
        log.info("Fetching my profile");

        User user = userRepository.findById(1L).orElseThrow(
                () -> new UserNotFoundException("User not found")
        );

        return mapProfileToProfileResponse(user.getProfile());
    }


    public ProfileResponse updateMyProfile(ProfileRequest profileRequest) {
        log.info("Updating my profile");

        User user = userRepository.findById(1L).orElseThrow(
                () -> new UserNotFoundException("User not found")
        );

        Profile profile = user.getProfile();

        profile.setFirstName(profileRequest.getFirstName());
        profile.setLastName(profileRequest.getLastName());
        profile.setProfilePicture(profileRequest.getProfilePicture());
        profile.setBirthDate(profileRequest.getBirthDate());
        profile.setBirthPlace(profileRequest.getBirthPlace());
        profile.setBio(profileRequest.getBio());
        profile.setPreferences(profileRequest.getPreferences());

        return mapProfileToProfileResponse(profile);
    }

    public ProfileResponse createMyProfile(ProfileRequest profileRequest) {
        log.info("Creating my profile");

        User user = userRepository.findById(1L).orElseThrow(
                () -> new UserNotFoundException("User not found")
        );


        return profileResponseMapper.apply(
                profileService.createProfile(
                        userProfileResponseMapper.apply(profileRequest)
                )
        );
    }

    public void deleteMyProfile() {
        log.info("Deleting my profile");

        User user = userRepository.findById(1L).orElseThrow(
                () -> new UserNotFoundException("User not found")
        );

        profileService.deleteProfile(user.getProfile().getId());
    }


    public List<ActorResponse> getMyFavoritesActors() {
        log.info("Fetching my favorite actors");

        User user = userRepository.findById(1L).orElseThrow(
                () -> new UserNotFoundException("User not found")
        );

        // Call to the movie service to get the favorite actors
        return List.of();
    }

    public ActorResponse getMyFavoriteActor(Long id) {
        log.info("Fetching my favorite actor with id {}", id);

        User user = userRepository.findById(1L).orElseThrow(
                () -> new UserNotFoundException("User not found")
        );

        // Call to the movie service to get the favorite actor
        return ActorResponse.builder()
                .build();
    }

    public void createMyFavoriteActor(Long id) {
        // Build after implementing the FavoriteActor service
    }

    public void deleteMyFavoriteActor(Long id) {
        // Build after implementing the FavoriteActor service
    }

    public List<DirectorResponse> getMyFavoritesDirectors() {
        log.info("Fetching my favorite directors");

        User user = userRepository.findById(1L).orElseThrow(
                () -> new UserNotFoundException("User not found")
        );

        // Call to the movie service to get the favorite directors
        return List.of();
    }

    public DirectorResponse getMyFavoriteDirector(Long id) {
        log.info("Fetching my favorite director with id {}", id);

        User user = userRepository.findById(1L).orElseThrow(
                () -> new UserNotFoundException("User not found")
        );

        // Call to the movie service to get the favorite director
        return DirectorResponse.builder()
                .build();
    }

    public void createMyFavoriteDirector(Long id) {
        log.info("Creating my favorite director with id {}", id);

        User user = userRepository.findById(1L).orElseThrow(
                () -> new UserNotFoundException("User not found")
        );

        // Implement after implementing the FavoriteDirector service
    }

    public void deleteMyFavoriteDirector(Long id) {
        log.info("Deleting my favorite director with id {}", id);

        User user = userRepository.findById(1L).orElseThrow(
                () -> new UserNotFoundException("User not found")
        );

        // Implement after implementing the FavoriteDirector service
    }

    public List<MovieResponse> getMyFavoritesMovies() {
        log.info("Fetching my favorite movies");

        User user = userRepository.findById(1L).orElseThrow(
                () -> new UserNotFoundException("User not found")
        );

        // Call to the movie service to get the favorite movies
        return List.of();
    }

    public MovieResponse getMyFavoriteMovie(Long id) {
        log.info("Fetching my favorite movie with id {}", id);

        User user = userRepository.findById(1L).orElseThrow(
                () -> new UserNotFoundException("User not found")
        );

        // Call to the movie service to get the favorite movie
        return MovieResponse.builder()
                .build();
    }

    public void createMyFavoriteMovie(Long id) {
        log.info("Creating my favorite movie with id {}", id);

        User user = userRepository.findById(1L).orElseThrow(
                () -> new UserNotFoundException("User not found")
        );

        // Implement after implementing the FavoriteMovie service
    }

    public void deleteMyFavoriteMovie(Long id) {
        log.info("Deleting my favorite movie with id {}", id);

        User user = userRepository.findById(1L).orElseThrow(
                () -> new UserNotFoundException("User not found")
        );

        // Implement after implementing the FavoriteMovie service
    }

    public List<MovieResponse> getMySavedMovies() {
        log.info("Fetching my saved movies");

        User user = userRepository.findById(1L).orElseThrow(
                () -> new UserNotFoundException("User not found")
        );

        // Call to the movie service to get the saved movies
        return List.of();
    }

    public MovieResponse getMySavedMovie(Long id) {
        log.info("Fetching my saved movie with id {}", id);

        User user = userRepository.findById(1L).orElseThrow(
                () -> new UserNotFoundException("User not found")
        );

        // Call to the movie service to get the saved movie
        return MovieResponse.builder()
                .build();
    }

    public void createMySavedMovie(Long id) {
        log.info("Creating my saved movie with id {}", id);

        User user = userRepository.findById(1L).orElseThrow(
                () -> new UserNotFoundException("User not found")
        );

        // Implement after implementing the SavedMovie service
    }

    public void deleteMySavedMovie(Long id) {
        log.info("Deleting my saved movie with id {}", id);

        User user = userRepository.findById(1L).orElseThrow(
                () -> new UserNotFoundException("User not found")
        );

        // Implement after implementing the SavedMovie service
    }

    public List<MovieResponse> getMyWatchedMovies() {
        log.info("Fetching my watched movies");

        User user = userRepository.findById(1L).orElseThrow(
                () -> new UserNotFoundException("User not found")
        );

        // Call to the movie service to get the watched movies
        return List.of();
    }

    public MovieResponse getMyWatchedMovie(Long id) {
        log.info("Fetching my watched movie with id {}", id);

        User user = userRepository.findById(1L).orElseThrow(
                () -> new UserNotFoundException("User not found")
        );

        // Call to the movie service to get the watched movie
        return MovieResponse.builder()
                .build();
    }

    public void createMyWatchedMovie(Long id) {
        log.info("Creating my watched movie with id {}", id);

        User user = userRepository.findById(1L).orElseThrow(
                () -> new UserNotFoundException("User not found")
        );

        // Implement after implementing the WatchedMovie service
    }

    public void deleteMyWatchedMovie(Long id) {
        log.info("Deleting my watched movie with id {}", id);

        User user = userRepository.findById(1L).orElseThrow(
                () -> new UserNotFoundException("User not found")
        );

        // Implement after implementing the WatchedMovie service
    }

    public List<MovieResponse> getMyWatchlistMovies() {
        log.info("Fetching my watchlist movies");

        User user = userRepository.findById(1L).orElseThrow(
                () -> new UserNotFoundException("User not found")
        );

        // Call to the movie service to get the watchlist movies
        return List.of();
    }

    public MovieResponse getMyWatchlistMovie(Long id) {
        log.info("Fetching my watchlist movie with id {}", id);

        User user = userRepository.findById(1L).orElseThrow(
                () -> new UserNotFoundException("User not found")
        );

        // Call to the movie service to get the watchlist movie
        return MovieResponse.builder()
                .build();
    }

    public void createMyWatchlistMovie(Long id) {
        log.info("Creating my watchlist movie with id {}", id);

        User user = userRepository.findById(1L).orElseThrow(
                () -> new UserNotFoundException("User not found")
        );

        // Implement after implementing the WatchlistMovie service
    }

    public void deleteMyWatchlistMovie(Long id) {
        log.info("Deleting my watchlist movie with id {}", id);

        User user = userRepository.findById(1L).orElseThrow(
                () -> new UserNotFoundException("User not found")
        );

        // Implement after implementing the WatchlistMovie service
    }

//    public Optional<Object> getMyUserWithAuthorities() {
//        log.info("Fetching my user with authorities");
//
//        User user = userRepository.findById(1L).orElseThrow(
//                () -> new UserNotFoundException("User not found")
//        );
//
//        return Optional.of(
//                UserResponse.builder()
//                        .build()
//        );
//    }
}
