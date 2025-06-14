package org.matchmaking.persistence;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Users extends PanacheEntity {
    private String name;
    private String email;
    private String password;

    @ManyToMany
    @Fetch(FetchMode.JOIN)
    private List<Movie> favoriteMovies = new ArrayList<>();

    public Long getId() {
        return this.id;
    }
}
