package com.radouaneoubakhane.userservice.mapper;


public class UserMapper {
    public static com.radouaneoubakhane.userservice.dto.profile.ProfileRequest
        map(com.radouaneoubakhane.userservice.dto.user.ProfileRequest profileRequest) {
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

    public static com.radouaneoubakhane.userservice.dto.user.ProfileResponse
        map(com.radouaneoubakhane.userservice.dto.profile.ProfileResponse profileResponse) {
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
