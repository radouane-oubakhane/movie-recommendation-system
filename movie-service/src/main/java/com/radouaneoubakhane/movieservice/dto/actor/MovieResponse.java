package com.radouaneoubakhane.movieservice.dto.actor;


import com.radouaneoubakhane.movieservice.enums.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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
    private DirectorResponse director;
    private Genre genre;
}
