package com.radouaneoubakhane.catalogservice.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieRecommendationResponse {
    private String id;
    private MovieResponse movie;
}
