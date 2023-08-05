package com.radouaneoubakhane.userservice.service.impl;


import com.radouaneoubakhane.userservice.dto.profile.ProfileRequest;
import com.radouaneoubakhane.userservice.dto.profile.ProfileResponse;
import com.radouaneoubakhane.userservice.dto.profile.UserResponse;
import com.radouaneoubakhane.userservice.entity.Profile;
import com.radouaneoubakhane.userservice.entity.User;
import com.radouaneoubakhane.userservice.exception.profile.ProfileNotFoundException;
import com.radouaneoubakhane.userservice.repository.ProfileRepository;
import com.radouaneoubakhane.userservice.service.ProfileService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    public List<ProfileResponse> getProfiles() {
        log.info("Fetching all profiles");

        List<Profile> profiles = (List<Profile>) profileRepository.findAll();

        return profiles.stream().map(this::mapProfileToProfileResponse).toList();
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
                .user(mapUserToUserResponse(profile.getUser()))
                .build();
    }

    private UserResponse mapUserToUserResponse(User user) {
        return UserResponse.builder()
                        .id(user.getId())
                        .username(user.getUsername())
                        .email(user.getEmail())
                        .build();
    }

    public ProfileResponse getMyProfile() {
        log.info("Fetching my profile");

        Profile profile = profileRepository.findByUserId(1L).orElseThrow(
                () -> new ProfileNotFoundException("Profile not found")
        );

        return mapProfileToProfileResponse(profile);
    }

    public ProfileResponse updateMyProfile(ProfileRequest profileRequest) {
        log.info("Updating my profile");

        Profile profile = profileRepository.findByUserId(1L).orElseThrow(
                () -> new ProfileNotFoundException("Profile not found")
        );

        profile.setLastName(profileRequest.getLastName());
        profile.setFirstName(profileRequest.getFirstName());
        profile.setProfilePicture(profileRequest.getProfilePicture());
        profile.setBirthDate(profileRequest.getBirthDate());
        profile.setBirthPlace(profileRequest.getBirthPlace());
        profile.setBio(profileRequest.getBio());
        profile.setPreferences(profileRequest.getPreferences());

        return mapProfileToProfileResponse(profile);
    }

    public ProfileResponse createMyProfile(ProfileRequest profileRequest) {
        log.info("Creating my profile");

        if (profileRepository.findByUserId(1L).isPresent()) {
            throw new IllegalStateException("Profile already exists");
        }

        Profile profile = Profile.builder()
                .firstName(profileRequest.getFirstName())
                .lastName(profileRequest.getLastName())
                .profilePicture(profileRequest.getProfilePicture())
                .birthDate(profileRequest.getBirthDate())
                .birthPlace(profileRequest.getBirthPlace())
                .bio(profileRequest.getBio())
                .preferences(profileRequest.getPreferences())
                .user(User.builder().id(1L).build())
                .build();

        return mapProfileToProfileResponse(profileRepository.save(profile));
    }

    public void deleteMyProfile() {
        log.info("Deleting my profile");

        Profile profile = profileRepository.findByUserId(1L).orElseThrow(
                () -> new ProfileNotFoundException("Profile not found")
        );
        ///
    }

    public ProfileResponse getProfile(Long id) {
        log.info("Fetching profile with id {}", id);

        Profile profile = profileRepository.findById(id).orElseThrow(
                () -> new ProfileNotFoundException("Profile not found")
        );

        return mapProfileToProfileResponse(profile);
    }

    public ProfileResponse updateProfile(Long id, ProfileRequest profileRequest) {
        log.info("Updating profile with id {}", id);

        Profile profile = profileRepository.findById(id).orElseThrow(
                () -> new ProfileNotFoundException("Profile not found")
        );

        profile.setFirstName(profileRequest.getFirstName());
        profile.setLastName(profileRequest.getLastName());
        profile.setProfilePicture(profileRequest.getProfilePicture());
        profile.setBirthDate(profileRequest.getBirthDate());
        profile.setBirthPlace(profileRequest.getBirthPlace());
        profile.setBio(profileRequest.getBio());
        profile.setPreferences(profileRequest.getPreferences());

        return mapProfileToProfileResponse(profile);
    }

    public ProfileResponse createProfile(ProfileRequest profileRequest) {
        log.info("Creating profile");

        if (profileRepository.findByUserId(1L).isPresent()) {
            throw new IllegalStateException("Profile already exists");
        }

        User user = User.builder().id(1L).build();

        Profile profile = Profile.builder()
                .firstName(profileRequest.getFirstName())
                .lastName(profileRequest.getLastName())
                .profilePicture(profileRequest.getProfilePicture())
                .birthDate(profileRequest.getBirthDate())
                .birthPlace(profileRequest.getBirthPlace())
                .bio(profileRequest.getBio())
                .preferences(profileRequest.getPreferences())
                .user(user)
                .build();

        return mapProfileToProfileResponse(profileRepository.save(profile));
    }


    public void deleteProfile(Long id) {
        log.info("Deleting profile with id {}", id);

        Profile profile = profileRepository.findById(id).orElseThrow(
                () -> new ProfileNotFoundException("Profile not found")
        );

    }

    public List<ProfileResponse> searchProfiles(String name) {
        log.info("Searching profiles with name {}", name);

        List<Profile> profiles = profileRepository.findByFirstNameContainingOrLastNameContaining(name, name);

        return profiles.stream().map(this::mapProfileToProfileResponse).toList();
    }

}
