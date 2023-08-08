package com.radouaneoubakhane.movieservice.util;

import com.radouaneoubakhane.movieservice.domain.Actor;

import java.time.LocalDate;
import java.util.List;

public class ActorDataGenerator {
    public static Actor generateActor() {
        return Actor.builder()
                .id(1L)
                .firstName("Radouane")
                .lastName("Oubakhane")
                .picture("https://avatars.githubusercontent.com/u/24750237?v=4")
                .birthDate(LocalDate.of(1995, 8, 1))
                .birthPlace("Casablanca")
                .biography("Radouane Oubakhane is a Moroccan software engineer.")
                .build();
    }

    public static Actor generateActor(Long id) {
        return Actor.builder()
                .id(id)
                .firstName("John")
                .lastName("Doe")
                .birthDate(LocalDate.of(1995, 8, 1))
                .birthPlace("Casablanca")
                .biography("John Doe is a Moroccan software engineer.")
                .movies(List.of(MovieDataGenerator.generateMovie()))
                .build();
    }

    public static List<Actor> generateActors() {
        return null;
    }
}
