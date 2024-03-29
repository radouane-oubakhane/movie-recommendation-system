package com.radouaneoubakhane.userservice.dto.movie;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import com.radouaneoubakhane.userservice.enums.Genre;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieResponse {
    private Long id;
    private String title;
    private String description;
    private String poster;
    private LocalDate releaseDate;
    private int duration;
    private String language;
    private String country;
    private double averageRating;
    private DirectorResponse director;
    private List<ActorResponse> actors;
    private List<RatingResponse> ratings;
    private Genre genre;
}
