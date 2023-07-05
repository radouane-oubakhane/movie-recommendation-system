package com.radouaneoubakhane.movieservice.entity;


import com.radouaneoubakhane.movieservice.enums.Genre;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "movie")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String poster;
    private LocalDate releaseDate;
    private int duration;
    private String language;
    private String country;
    private double averageRating;

    @ManyToOne
    private Director director;

    @ManyToMany(mappedBy = "movies", fetch = FetchType.LAZY)
    private List<Actor> actors;

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Rating> ratings;

    @Enumerated(value = EnumType.STRING)
    private Genre genre;
}
