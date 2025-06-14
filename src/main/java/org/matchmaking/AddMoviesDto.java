package org.matchmaking;

import java.util.List;

import lombok.Data;

@Data
public class AddMoviesDto {
    List<Long> moviesIds;
}
