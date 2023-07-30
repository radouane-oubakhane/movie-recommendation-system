package com.radouaneoubakhane.userservice.service;

import com.radouaneoubakhane.userservice.dto.profile.ProfileRequest;
import com.radouaneoubakhane.userservice.dto.profile.ProfileResponse;

import java.util.List;

public interface ProfileService {
    ProfileResponse getMyProfile();

    ProfileResponse updateMyProfile(ProfileRequest profileRequest);

    ProfileResponse createMyProfile(ProfileRequest profileRequest);

    void deleteMyProfile();

    List<ProfileResponse> searchProfiles(String name);
}
