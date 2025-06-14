package org.matchmaking.persistence;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.matchmaking.enums.MovieGenre;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Movie extends PanacheEntity {
    
    private String title;
    
    @Enumerated(EnumType.STRING)
    private MovieGenre genre;
    
    private Integer releaseYear;
    
    @ManyToOne
    private MovieDirector director;
    
    private Double averageRating;

    @ManyToMany(mappedBy = "favoriteMovies")
    @Fetch(FetchMode.JOIN)
    List<Users> usersWhoFavorited = new ArrayList<>();
}
