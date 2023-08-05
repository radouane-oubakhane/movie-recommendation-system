package com.radouaneoubakhane.userservice.controller;

import com.radouaneoubakhane.userservice.dto.profile.ProfileRequest;
import com.radouaneoubakhane.userservice.dto.profile.ProfileResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ProfileController {

    ProfileResponse getMyProfile();

    ProfileResponse updateMyProfile(ProfileRequest profileRequest);

    ProfileResponse createMyProfile(ProfileRequest profileRequest);

    void deleteMyProfile();

    List<ProfileResponse> searchProfiles(String name);
}
