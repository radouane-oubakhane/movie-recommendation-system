package com.radouaneoubakhane.userservice.dto.director;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import com.radouaneoubakhane.userservice.enums.Genre;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieResponse {
    private Long id;
    private String title;
    private String poster;
    private LocalDate releaseDate;
    private double averageRating;
    private Genre genre;
}
