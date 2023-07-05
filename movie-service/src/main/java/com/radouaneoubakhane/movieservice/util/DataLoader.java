package com.radouaneoubakhane.movieservice.util;


import com.radouaneoubakhane.movieservice.repository.ActorRepository;
import com.radouaneoubakhane.movieservice.repository.DirectorRepository;
import com.radouaneoubakhane.movieservice.repository.MovieRepository;
import com.radouaneoubakhane.movieservice.repository.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final MovieRepository movieRepository;
    private final ActorRepository actorRepository;
    private final DirectorRepository directorRepository;
    private final RatingRepository ratingRepository;

    @Override
    public void run(String... args) throws Exception {
//        directorRepository.save(DirectorDataGenerator.generateDirector());
//        actorRepository.save(ActorDataGenerator.generateActor());
//        movieRepository.save(MovieDataGenerator.generateMovie());
//        ratingRepository.save(RatingDataGenerator.generateRating());
    }
}
