package com.radouaneoubakhane.movieservice.dto.Director;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DirectorRequest {
    private String firstName;
    private String lastName;
    private String picture;
    private LocalDate birthDate;
    private String birthPlace;
    private String biography;
}
