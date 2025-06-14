package org.matchmaking.dto;

import org.matchmaking.enums.MovieGenre;
import org.matchmaking.persistence.Movie;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MovieDto {

    private Long id;
    private String title;
    private MovieGenre genre;
    private Integer releaseYear;
    private MovieDirectorDto director;
    private Double averageRating;

    public MovieDto(Movie movie) {
        this.id = movie.id;
        this.title = movie.getTitle();
        this.genre = movie.getGenre();
        this.releaseYear = movie.getReleaseYear();
        this.director = new MovieDirectorDto(movie.getDirector());
        this.averageRating = movie.getAverageRating();
    }
}
