package com.radouaneoubakhane.userservice.dto.user;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DirectorResponse {
    private Long id;
    private String name;
    private String profilePath;
}
