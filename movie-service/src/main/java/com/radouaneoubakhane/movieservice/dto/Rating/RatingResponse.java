package com.radouaneoubakhane.movieservice.dto.Rating;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RatingResponse {
    private Long id;
    private Long userId;
    private int rating;
    private String review;
    private String timestamp;
    private MovieResponse movie;
}
