package com.radouaneoubakhane.movieservice.dto.Actor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActorResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String picture;
    private LocalDate birthDate;
    private String birthPlace;
    private String biography;
    private List<MovieResponse> movies;
}
