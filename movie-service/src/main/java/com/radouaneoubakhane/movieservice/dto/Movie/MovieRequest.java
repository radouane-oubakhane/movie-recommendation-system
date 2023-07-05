package com.radouaneoubakhane.movieservice.dto.Movie;


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
public class MovieRequest {
    private String title;
    private String description;
    private String poster;
    private LocalDate releaseDate;
    private int duration;
    private String language;
    private String country;
    private Genre genre;
}
