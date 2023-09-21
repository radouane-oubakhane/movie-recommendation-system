package com.radouaneoubakhane.userservice.mapper.user;

import org.springframework.stereotype.Service;
import java.util.function.Function;

@Service
public class UserProfileResponseMapper implements Function
        <com.radouaneoubakhane.userservice.dto.user.ProfileRequest,
        com.radouaneoubakhane.userservice.dto.profile.ProfileRequest>
{

    @Override
    public com.radouaneoubakhane.userservice.dto.profile.ProfileRequest
    apply(com.radouaneoubakhane.userservice.dto.user.ProfileRequest profileRequest)
    {
        return com.radouaneoubakhane.userservice.dto.profile.ProfileRequest.builder()
                .firstName(profileRequest.getFirstName())
                .lastName(profileRequest.getLastName())
                .profilePicture(profileRequest.getProfilePicture())
                .birthDate(profileRequest.getBirthDate())
                .birthPlace(profileRequest.getBirthPlace())
                .bio(profileRequest.getBio())
                .preferences(profileRequest.getPreferences())
                .build();
    }
}
