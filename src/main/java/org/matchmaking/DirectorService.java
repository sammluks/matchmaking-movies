package org.matchmaking;

import java.util.Comparator;
import java.util.List;

import org.matchmaking.persistence.MovieDirector;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class DirectorService {

    public MovieDirectorDto createDirector(MovieDirectorDto dto) {
        MovieDirector director = new MovieDirector();
        director.setName(dto.getName());

        director.persistAndFlush();

        return new MovieDirectorDto(director);
    }

    public List<MovieDirectorDto> listDirectors() {
        return MovieDirector.streamAll()
                .map(director -> (MovieDirector) director)
                .sorted(Comparator.comparingLong(MovieDirector::getId))
                .map(MovieDirectorDto::new)
                .toList();
    }

    public boolean removeDirector(Long id) {
        return MovieDirector.deleteById(id);
    }

    public MovieDirector getMovieDirector(Long directorId) {
        return MovieDirector.findByIdOptional(directorId).map(entity -> (MovieDirector) entity).orElseThrow(
                () -> new EntityNotFoundException("Não foi encontrado diretor com id: " + directorId + "!"));
    }

}
