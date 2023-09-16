package com.radouaneoubakhane.movieservice.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "director")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Director {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String picture;
    private LocalDate birthDate;
    private String birthPlace;
    private String biography;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "director_id")
    private List<Movie> movies;
}
