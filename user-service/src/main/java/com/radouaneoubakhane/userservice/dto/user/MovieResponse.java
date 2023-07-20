package com.radouaneoubakhane.userservice.dto.user;


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
    private String overview;
    private String posterPath;
    private String backdropPath;
    private String releaseDate;
    private Integer runtime;
    private Double voteAverage;
    private Integer voteCount;
    private Boolean isWatched;
    private Boolean isSaved;
    private ActorResponse[] actors;
    private DirectorResponse[] directors;
}
