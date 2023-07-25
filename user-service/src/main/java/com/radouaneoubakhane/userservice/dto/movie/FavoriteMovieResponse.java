package com.radouaneoubakhane.userservice.dto.movie;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteMovieResponse {
    private Long id;
    private MovieResponse movie;
}
