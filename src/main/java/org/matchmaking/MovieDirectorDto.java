package org.matchmaking;

import org.matchmaking.persistence.MovieDirector;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MovieDirectorDto {
    private Long id;
    private String name;

    public MovieDirectorDto(MovieDirector director) {
        this.id = director.id;
        this.name = director.getName();
    }
}
