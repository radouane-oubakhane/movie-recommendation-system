package com.radouaneoubakhane.userservice.mapper.profile;

import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ProfileResponseMapper implements Function
        <com.radouaneoubakhane.userservice.dto.profile.ProfileResponse,
        com.radouaneoubakhane.userservice.dto.user.ProfileResponse>
{

    @Override
    public com.radouaneoubakhane.userservice.dto.user.ProfileResponse
    apply(com.radouaneoubakhane.userservice.dto.profile.ProfileResponse profileResponse)
    {
        return com.radouaneoubakhane.userservice.dto.user.ProfileResponse.builder()
                .id(profileResponse.getId())
                .firstName(profileResponse.getFirstName())
                .lastName(profileResponse.getLastName())
                .profilePicture(profileResponse.getProfilePicture())
                .birthDate(profileResponse.getBirthDate())
                .birthPlace(profileResponse.getBirthPlace())
                .bio(profileResponse.getBio())
                .preferences(profileResponse.getPreferences())
                .build();
    }
}
