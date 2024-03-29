package com.radouaneoubakhane.userservice.dto.user;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActorResponse {
    private Long id;
    private String name;
    private String character;
    private String profilePath;
}
