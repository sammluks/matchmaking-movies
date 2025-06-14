package org.matchmaking.persistence;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class MovieDirector extends PanacheEntity {
    private String name;

    public Long getId() {
        return this.id;
    }
}
