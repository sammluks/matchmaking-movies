package org.matchmaking.dto;

import java.util.List;

import lombok.Data;

@Data
public class AddMoviesDto {
    List<Long> moviesIds;
}
