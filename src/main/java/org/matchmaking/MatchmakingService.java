package org.matchmaking;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.matchmaking.enums.MovieGenre;
import org.matchmaking.persistence.Movie;
import org.matchmaking.persistence.MovieDirector;
import org.matchmaking.persistence.Users;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class MatchmakingService {

    @Inject
    UserService userService;

    public List<MatchUserDto> listCompatiblePairs(Long userId) {
        Users user = userService.getUser(userId);

        Map<Users, Double> matchMap = new HashMap<>();
        List<Movie> userMovies = user.getFavoriteMovies();
        List<MovieGenre> genres = userMovies.stream().map(Movie::getGenre).distinct().toList();
        List<MovieDirector> directors = userMovies.stream().map(Movie::getDirector).distinct().toList();

        userService.getUsers().stream().filter(otherUser -> !user.getId().equals(otherUser.getId()))
                .forEach(otherUser -> {

                    List<Movie> moviesIntersection = new ArrayList<>(otherUser.getFavoriteMovies());

                    moviesIntersection.retainAll(userMovies);
                    long similarMoviesCount = moviesIntersection.size();

                    long similarGenresCount = otherUser.getFavoriteMovies().stream().map(Movie::getGenre)
                            .distinct().filter(otherUserGenre -> genres.contains(otherUserGenre)).count();

                    long similarDirectorsCount = otherUser.getFavoriteMovies().stream().map(Movie::getDirector)
                            .distinct()
                            .filter(otherUserDirector -> directors.contains(otherUserDirector)).count();

                    double percentage = ((double) (similarMoviesCount + similarGenresCount + similarDirectorsCount)
                            / (Math.min(userMovies.size(), otherUser.getFavoriteMovies().size()) * 3)) * 100.0;

                    matchMap.put(otherUser, percentage);
                });

        return matchMap.entrySet().stream().sorted(Comparator.comparingDouble(Map.Entry<Users, Double>::getValue).reversed())
                .limit(10)
                .map(entry -> new MatchUserDto(entry.getKey(), entry.getValue())).toList();
    }
}
