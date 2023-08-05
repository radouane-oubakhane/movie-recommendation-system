package com.radouaneoubakhane.userservice.controller.impl;


import com.radouaneoubakhane.userservice.dto.profile.ProfileRequest;
import com.radouaneoubakhane.userservice.dto.profile.ProfileResponse;
import com.radouaneoubakhane.userservice.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/profiles")
public class ProfileControllerImpl implements com.radouaneoubakhane.userservice.controller.ProfileController {

    private final ProfileService profileService;

    @Override
    @GetMapping
    public ProfileResponse getMyProfile() {
        return profileService.getMyProfile();
    }

    @Override
    @PutMapping
    public ProfileResponse updateMyProfile(@RequestBody ProfileRequest profileRequest) {
        return profileService.updateMyProfile(profileRequest);
    }

    @Override
    @PostMapping
    public ProfileResponse createMyProfile(@RequestBody ProfileRequest profileRequest) {
        return profileService.createMyProfile(profileRequest);
    }

    @Override
    @DeleteMapping
    public void deleteMyProfile() {
        profileService.deleteMyProfile();
    }

    // Admin operations

//    @GetMapping
//    public List<ProfileResponse> getProfiles() {
//        return profileService.getProfiles();
//    }
//
//    @GetMapping("/{id}")
//    public ProfileResponse getProfile(@PathVariable Long id) {
//        return profileService.getProfile(id);
//    }
//
//    @PutMapping("/{id}")
//    public ProfileResponse updateProfile(@PathVariable Long id, @RequestBody ProfileRequest profileRequest) {
//        return profileService.updateProfile(id, profileRequest);
//    }
//
//    @PostMapping
//    public ProfileResponse createProfile(@RequestBody ProfileRequest profileRequest) {
//        return profileService.createProfile(profileRequest);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteProfile(@PathVariable Long id) {
//        profileService.deleteProfile(id);
//    }

    // Other operations

    @Override
    @GetMapping("/search")
    public List<ProfileResponse> searchProfiles(@RequestParam String name) {
        return profileService.searchProfiles(name);
    }
}
