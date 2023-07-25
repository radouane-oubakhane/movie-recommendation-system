package com.radouaneoubakhane.userservice.dto.movie;


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
    private String firstName;
    private String lastName;
    private String picture;
}
