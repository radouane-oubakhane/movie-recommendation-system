package com.radouaneoubakhane.userservice.controller;


import com.radouaneoubakhane.userservice.dto.profile.ProfileRequest;
import com.radouaneoubakhane.userservice.dto.profile.ProfileResponse;
import com.radouaneoubakhane.userservice.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/profile")
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping
    public List<ProfileResponse> getProfiles() {
        return profileService.getProfiles();
    }

    @GetMapping("/me")
    public ProfileResponse getMyProfile() {
        return profileService.getMyProfile();
    }

    @PutMapping("/me")
    public ProfileResponse updateMyProfile(@RequestBody ProfileRequest profileRequest) {
        return profileService.updateMyProfile(profileRequest);
    }

    @PostMapping("/me")
    public ProfileResponse createMyProfile(@RequestBody ProfileRequest profileRequest) {
        return profileService.createMyProfile(profileRequest);
    }

    @DeleteMapping("/me")
    public void deleteMyProfile() {
        profileService.deleteMyProfile();
    }

    @GetMapping("/{id}")
    public ProfileResponse getProfile(@PathVariable Long id) {
        return profileService.getProfile(id);
    }

    @PutMapping("/{id}")
    public ProfileResponse updateProfile(@PathVariable Long id, @RequestBody ProfileRequest profileRequest) {
        return profileService.updateProfile(id, profileRequest);
    }

    @PostMapping("/{id}")
    public ProfileResponse createProfile(@PathVariable Long id, @RequestBody ProfileRequest profileRequest) {
        return profileService.createProfile(id, profileRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteProfile(@PathVariable Long id) {
        profileService.deleteProfile(id);
    }

    // Other operations

    @GetMapping("/search")
    public List<ProfileResponse> searchProfiles(@RequestParam String name) {
        return profileService.searchProfiles(name);
    }
}