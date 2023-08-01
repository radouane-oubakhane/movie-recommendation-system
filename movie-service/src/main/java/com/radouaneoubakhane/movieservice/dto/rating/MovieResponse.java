package com.radouaneoubakhane.movieservice.dto.rating;


import com.radouaneoubakhane.movieservice.enums.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieResponse {
    private Long id;
    private String title;
    private double averageRating;
    private Genre genre;
}
