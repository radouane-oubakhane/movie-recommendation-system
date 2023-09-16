package com.radouaneoubakhane.movieservice.util;

import com.radouaneoubakhane.movieservice.model.Director;

import java.time.LocalDate;
import java.util.List;

public class DirectorDataGenerator {

    public static Director generateDirector() {
        return Director.builder()
                .id(1L)
                .firstName("Rayan")
                .lastName("Oubakhane")
                .picture("https://upload.wikimedia.org/wikipedia/commons/thumb/6/6e/Rayan_Oubakhane.jpg/220px-Rayan_Oubakhane.jpg")
                .birthDate(LocalDate.of(1998, 8, 8))
                .birthPlace("Casablanca")
                .biography("Rayan Oubakhane is a Moroccan actor, director, and producer. He is the founder of the production company Rayan Oubakhane Productions.")
                .build();
    }

    public static List<Director> generateDirectors() {
        return null;
    }
}
