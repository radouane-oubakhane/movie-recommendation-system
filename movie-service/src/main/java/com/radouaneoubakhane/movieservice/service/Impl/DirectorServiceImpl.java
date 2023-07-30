package com.radouaneoubakhane.movieservice.service.Impl;


import com.radouaneoubakhane.movieservice.dto.Director.DirectorRequest;
import com.radouaneoubakhane.movieservice.dto.Director.DirectorResponse;
import com.radouaneoubakhane.movieservice.dto.Director.MovieResponse;
import com.radouaneoubakhane.movieservice.entity.Director;
import com.radouaneoubakhane.movieservice.entity.Movie;
import com.radouaneoubakhane.movieservice.exception.Director.DirectorNotFoundException;
import com.radouaneoubakhane.movieservice.exception.Movie.MovieNotFoundException;
import com.radouaneoubakhane.movieservice.repository.DirectorRepository;
import com.radouaneoubakhane.movieservice.repository.MovieRepository;
import com.radouaneoubakhane.movieservice.service.DirectorService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class DirectorServiceImpl implements DirectorService {

    private final DirectorRepository directorRepository;
    private final MovieRepository movieRepository;

    public List<DirectorResponse> getDirectors() {
        log.info("Fetching all directors");

        List<Director> directors = directorRepository.findAll();

        return directors.stream().map(this::mapDirectorToDirectorResponse).toList();
    }

    private DirectorResponse mapDirectorToDirectorResponse(Director director) {
        return DirectorResponse.builder()
                .id(director.getId())
                .firstName(director.getFirstName())
                .lastName(director.getLastName())
                .picture(director.getPicture())
                .birthDate(director.getBirthDate())
                .birthPlace(director.getBirthPlace())
                .biography(director.getBiography())
                .movies(mapMoviesToMovieResponses(director.getMovies()))
                .build();
    }

    private List<MovieResponse> mapMoviesToMovieResponses(List<Movie> movies) {
        if (movies == null) {
            return null;
        }
        return movies.stream().map(movie -> MovieResponse.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .poster(movie.getPoster())
                .releaseDate(movie.getReleaseDate())
                .averageRating(movie.getAverageRating())
                .genre(movie.getGenre())
                .build()).toList();
    }


    public DirectorResponse getDirector(Long id) {
        log.info("Fetching director with id: {}", id);

        Director director = directorRepository.findById(id).orElseThrow(
                () -> new DirectorNotFoundException("Director not found")
        );

        return mapDirectorToDirectorResponse(director);
    }

    public DirectorResponse createDirector(DirectorRequest director) {
        log.info("Creating director");

        Director directorToSave = Director.builder()
                .firstName(director.getFirstName())
                .lastName(director.getLastName())
                .picture(director.getPicture())
                .birthDate(director.getBirthDate())
                .birthPlace(director.getBirthPlace())
                .biography(director.getBiography())
                .build();

        return mapDirectorToDirectorResponse(directorRepository.save(directorToSave));
    }

    public DirectorResponse updateDirector(Long id, DirectorRequest director) {
        log.info("Updating director with id: {}", id);

        Director directorToUpdate = directorRepository.findById(id).orElseThrow(
                () -> new DirectorNotFoundException("Director not found")
        );

        directorToUpdate.setFirstName(director.getFirstName());
        directorToUpdate.setLastName(director.getLastName());
        directorToUpdate.setPicture(director.getPicture());
        directorToUpdate.setBirthDate(director.getBirthDate());
        directorToUpdate.setBirthPlace(director.getBirthPlace());
        directorToUpdate.setBiography(director.getBiography());

        return mapDirectorToDirectorResponse(directorRepository.save(directorToUpdate));
    }

    public void deleteDirector(Long id) {
        log.info("Deleting director with id: {}", id);

        Director directorToDelete = directorRepository.findById(id).orElseThrow(
                () -> new DirectorNotFoundException("Director not found")
        );

        directorRepository.delete(directorToDelete);
    }

    public List<DirectorResponse> searchDirectors(String name) {
        log.info("Searching for directors with name: {}", name);

        List<Director> directors = directorRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(name, name);

        return directors.stream().map(this::mapDirectorToDirectorResponse).toList();
    }

    public List<MovieResponse> getDirectorMovies(Long directorId) {
        log.info("Fetching movies for director with id: {}", directorId);

        Director director = directorRepository.findById(directorId).orElseThrow(
                () -> new DirectorNotFoundException("Director not found")
        );

        return mapMoviesToMovieResponses(director.getMovies());
    }

    public void addDirectorMovie(Long directorId, Long movieId) {
        log.info("Adding movie with id: {} to director with id: {}", movieId, directorId);

        Director director = directorRepository.findById(directorId).orElseThrow(
                () -> new DirectorNotFoundException("Director not found")
        );

        Movie movie = movieRepository.findById(movieId).orElseThrow(
                () -> new DirectorNotFoundException("Movie not found")
        );

        if (director.getMovies().contains(movie)) {
            throw new IllegalArgumentException("Movie already exists");
        }

        director.getMovies().add(movie);
    }

    public void removeDirectorMovie(Long directorId, Long movieId) {
        log.info("Removing movie with id: {} from director with id: {}", movieId, directorId);

        Director director = directorRepository.findById(directorId).orElseThrow(
                () -> new DirectorNotFoundException("Director not found")
        );

        Movie movie = movieRepository.findById(movieId).orElseThrow(
                () -> new DirectorNotFoundException("Movie not found")
        );

        if (!director.getMovies().contains(movie)) {
            throw new MovieNotFoundException("Movie not found");
        }

        director.getMovies().remove(movie);
    }

    public List<DirectorResponse> getDirectorsByIds(List<Long> id) {
        log.info("Fetching directors with ids: {}", id);

        List<Director> directors = directorRepository.findAllById(id);

        return directors.stream().map(this::mapDirectorToDirectorResponse).toList();
    }
}
