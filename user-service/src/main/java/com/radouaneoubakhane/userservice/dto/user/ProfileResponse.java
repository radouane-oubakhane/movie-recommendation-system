package com.radouaneoubakhane.userservice.dto.user;

import com.radouaneoubakhane.userservice.dto.profile.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String profilePicture;
    private LocalDate birthDate;
    private String birthPlace;
    private String bio;
    private String preferences;
}