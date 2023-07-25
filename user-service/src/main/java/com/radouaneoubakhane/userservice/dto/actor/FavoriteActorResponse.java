package com.radouaneoubakhane.userservice.dto.actor;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteActorResponse {
    private Long id;
    private ActorResponse actor;
}
