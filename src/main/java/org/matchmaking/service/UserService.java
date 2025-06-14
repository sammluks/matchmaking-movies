package org.matchmaking.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.matchmaking.dto.AddMoviesDto;
import org.matchmaking.dto.UserDto;
import org.matchmaking.persistence.Movie;
import org.matchmaking.persistence.Users;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
@Transactional
public class UserService {

    @Inject
    MovieService movieService;

    public UserDto addUser(UserDto dto) {
        Users user = new Users();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());

        user.persistAndFlush();

        return new UserDto(user);
    }

    public Users getUser(Long userId) {
        return Users.findByIdOptional(userId).map(user -> (Users) user)
                .orElseThrow(() -> new NotFoundException("Não foi encontrado usuário com id " + userId + "!"));
    }

    public boolean removeUser(Long userId) {
        return Users.deleteById(userId);
    }

    public UserDto updateUser(UserDto dto) {
        return Users.findByIdOptional(dto.getId()).map(entity -> {
            Users user = (Users) entity;
            user.setName(dto.getName());
            user.setPassword(dto.getPassword());
            return new UserDto(user);
        }).orElseThrow(() -> new NotFoundException("Não foi encontrado usuário com id " + dto.getId() + "!"));
    }

    public Optional<UserDto> getUser(String email) {
        return Users.find("email", email).firstResultOptional().map(user -> new UserDto((Users) user));
    }

    public List<Users> getUsers() {
        return Users.streamAll().map(user -> (Users) user).sorted(Comparator.comparingLong(Users::getId)).toList();
    }

    public void addUserMovies(Long userId, AddMoviesDto addMoviesDto) {
        Users user = (Users) Users.findById(userId);
        List<Movie> likedMovies = user.getFavoriteMovies();
        movieService.getMovie(1L);
        List<Movie> newMovies = addMoviesDto.getMoviesIds().stream().map(id -> (Movie) Movie.findById(id)).toList();
        likedMovies.addAll(newMovies);
        user.setFavoriteMovies(newMovies);
    }

}
