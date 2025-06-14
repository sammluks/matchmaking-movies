package org.matchmaking.service;

import org.matchmaking.dto.MovieDto;
import org.matchmaking.persistence.Movie;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class MovieService {

    @Inject
    DirectorService directorService;

    public MovieDto createMovie(MovieDto dto) {
        Movie movie = new Movie();
        movie.setDirector(directorService.getMovieDirector(dto.getDirector().getId()));
        movie.setTitle(dto.getTitle());
        movie.setGenre(dto.getGenre());
        movie.setReleaseYear(dto.getReleaseYear());
        movie.setAverageRating(dto.getAverageRating());

        movie.persistAndFlush();

        return new MovieDto(movie);
    }

    public MovieDto updateMovie(MovieDto dto) {
        Movie movie = Movie.findByIdOptional(dto.getId())
                .map(entity -> (Movie) entity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Não foi encontrado filme com id: " + dto.getDirector().getId() + "!"));
                        
        movie.setTitle(dto.getTitle());
        movie.setGenre(dto.getGenre());
        movie.setReleaseYear(dto.getReleaseYear());
        movie.setAverageRating(dto.getAverageRating());
        movie.setDirector(directorService.getMovieDirector(dto.getDirector().getId()));

        return new MovieDto(movie);
    }

    public Movie getMovie(Long movieId) {
        return (Movie) Movie.findById(movieId);
    }
}
