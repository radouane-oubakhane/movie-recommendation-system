package com.radouaneoubakhane.catalogservice.dto.movie;

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
    private String firstName;
    private String lastName;
    private String picture;
}
