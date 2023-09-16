package com.radouaneoubakhane.movieservice.service.impl;

import com.radouaneoubakhane.movieservice.dto.actor.DirectorResponse;
import com.radouaneoubakhane.movieservice.dto.actor.MovieResponse;
import com.radouaneoubakhane.movieservice.dto.actor.ActorRequest;
import com.radouaneoubakhane.movieservice.dto.actor.ActorResponse;
import com.radouaneoubakhane.movieservice.model.Actor;
import com.radouaneoubakhane.movieservice.model.Director;
import com.radouaneoubakhane.movieservice.model.Movie;
import com.radouaneoubakhane.movieservice.exception.Actor.ActorNotFoundException;
import com.radouaneoubakhane.movieservice.exception.Movie.MovieNotFoundException;
import com.radouaneoubakhane.movieservice.mapper.ActorMapper;
import com.radouaneoubakhane.movieservice.repository.ActorRepository;
import com.radouaneoubakhane.movieservice.service.ActorService;
import com.radouaneoubakhane.movieservice.service.MovieService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@Transactional
@Slf4j
public class ActorServiceImpl implements ActorService {

    private final ActorRepository actorRepository;
    private final MovieService movieService;



    public List<ActorResponse> getActors() {
        log.info("Fetching all actors");

        List<Actor> actors = actorRepository.findAll();

        return actors.stream().map(this::mapActorToActorResponse).toList();
    }

    @Override
    public Page<ActorResponse> getActors(Pageable pageable) {
        log.info("Fetching all actors");

        Page<Actor> actors = actorRepository.findAll(pageable);

        return actors.map(this::mapActorToActorResponse);
    }


    private ActorResponse mapActorToActorResponse(Actor actor) {
        return ActorResponse.builder()
                .id(actor.getId())
                .firstName(actor.getFirstName())
                .lastName(actor.getLastName())
                .picture(actor.getPicture())
                .birthDate(actor.getBirthDate())
                .birthPlace(actor.getBirthPlace())
                .biography(actor.getBiography())
                .movies(mapMoviesToMovieResponses(actor.getMovies()))
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
                .director(mapDirectorToDirectorResponse(movie.getDirector()))
                .genre(movie.getGenre())
                .build()).toList();
    }

    private DirectorResponse mapDirectorToDirectorResponse(Director director) {
        if (director == null) {
            return null;
        }
        return DirectorResponse.builder()
                .id(director.getId())
                .firstName(director.getFirstName())
                .lastName(director.getLastName())
                .build();
    }

    public ActorResponse getActor(Long id) {
        log.info("Fetching actor with id {}", id);

        Actor actor = actorRepository.findById(id).orElseThrow(
                () -> new ActorNotFoundException("Actor not found")
        );
        return mapActorToActorResponse(actor);
    }


    public ActorResponse createActor(ActorRequest actor) {
        log.info("Creating actor");
        Actor actorToCreate = Actor.builder()
                .firstName(actor.getFirstName())
                .lastName(actor.getLastName())
                .picture(actor.getPicture())
                .birthDate(actor.getBirthDate())
                .birthPlace(actor.getBirthPlace())
                .biography(actor.getBiography())
                .build();

        return mapActorToActorResponse(actorRepository.save(actorToCreate));
    }

    public ActorResponse updateActor(Long id, ActorRequest actor) {
        log.info("Updating actor with id {}", id);

        Actor actorToUpdate = actorRepository.findById(id).orElseThrow(
                () -> new ActorNotFoundException("Actor not found")
        );

        actorToUpdate.setFirstName(actor.getFirstName());
        actorToUpdate.setLastName(actor.getLastName());
        actorToUpdate.setPicture(actor.getPicture());
        actorToUpdate.setBirthDate(actor.getBirthDate());
        actorToUpdate.setBirthPlace(actor.getBirthPlace());
        actorToUpdate.setBiography(actor.getBiography());

        return mapActorToActorResponse(actorRepository.save(actorToUpdate));
    }

    public void deleteActor(Long id) {
        log.info("Deleting actor with id {}", id);

        Actor actorToDelete = actorRepository.findById(id).orElseThrow(
                () -> new ActorNotFoundException("Actor not found")
        );

        actorRepository.delete(actorToDelete);
    }

    public List<ActorResponse> searchActors(String name) {
        log.info("Searching actors with name {}", name);

        List<Actor> actors = actorRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(name, name);

        return actors.stream().map(this::mapActorToActorResponse).toList();
    }

    public List<MovieResponse> getActorMovies(Long actorId) {
        log.info("Fetching movies by actor with id {}", actorId);

        Actor actor = actorRepository.findById(actorId).orElseThrow(
                () -> new ActorNotFoundException("Actor not found")
        );

        return mapMoviesToMovieResponses(actor.getMovies());
    }

    public void addMovieToActor(Long actorId, Long movieId) {
        log.info("Adding movie with id {} to actor with id {}", movieId, actorId);

        Actor actor = actorRepository.findById(actorId).orElseThrow(
                () -> new ActorNotFoundException("Actor not found")
        );

        MovieResponse movieResponse = ActorMapper.map(
                movieService.getMovie(movieId)
        );

        if (movieResponse == null) {
            throw new MovieNotFoundException("Movie not found");
        }

        if (actor.getMovies().stream().map(Movie::getId).toList().contains(movieId)) {
            throw new IllegalArgumentException("Movie already added to actor");
        }

        actor.getMovies().add(
                Movie.builder()
                        .id(movieResponse.getId())
                        .title(movieResponse.getTitle())
                        .poster(movieResponse.getPoster())
                        .releaseDate(movieResponse.getReleaseDate())
                        .averageRating(movieResponse.getAverageRating())
                        .director(Director.builder()
                                .id(movieResponse.getDirector().getId())
                                .firstName(movieResponse.getDirector().getFirstName())
                                .lastName(movieResponse.getDirector().getLastName())
                                .build())
                        .genre(movieResponse.getGenre())
                        .build()
        );
    }

    public void removeMovieFromActor(Long actorId, Long movieId) {
        log.info("Removing movie with id {} from actor with id {}", movieId, actorId);

        Actor actor = actorRepository.findById(actorId).orElseThrow(
                () -> new ActorNotFoundException("Actor not found")
        );

        MovieResponse movieResponse = ActorMapper.map(
                movieService.getMovie(movieId)
        );

        if (movieResponse == null) {
            throw new MovieNotFoundException("Movie not found");
        }

        Movie movieToRemove = actor.getMovies().stream().filter(movie -> movie.getId().equals(movieId)).findFirst().orElseThrow(
                () -> new MovieNotFoundException("Movie not found")
        );
        actor.getMovies().remove(movieToRemove);
    }

    public List<ActorResponse> getActorsByIds(List<Long> id) {
        log.info("Fetching actors by ids {}", id);

        List<Actor> actors = actorRepository.findAllById(id);

        return actors.stream().map(this::mapActorToActorResponse).toList();
    }
}

