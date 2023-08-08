package com.radouaneoubakhane.movieservice.util;

import com.radouaneoubakhane.movieservice.enums.Genre;
import com.radouaneoubakhane.movieservice.domain.Movie;

import java.time.LocalDate;
import java.util.List;

public class MovieDataGenerator {
    public static Movie generateMovie() {
        return Movie.builder()
                .id(1L)
                .title("The Godfather")
                .poster("https://image.tmdb.org/t/p/w1280/3bhkrj58Vtu7enYsRolD1fZdja1.jpg")
                .description("The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.")
                .releaseDate(LocalDate.of(1972, 3, 24))
                .duration(175)
                .language("English")
                .country("USA")
                .averageRating(9.2)
                .director(DirectorDataGenerator.generateDirector())
                .actors(List.of(ActorDataGenerator.generateActor()))
                .genre(Genre.CRIME)
                .build();
    }

    public static List<Movie> generateMovies() {
        return null;
    }


}
