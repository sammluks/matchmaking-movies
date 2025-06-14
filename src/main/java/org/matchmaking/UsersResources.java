package org.matchmaking;

import java.util.List;
import java.util.Optional;

import org.matchmaking.persistence.Movie;
import org.matchmaking.persistence.Users;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsersResources {

    @Inject
    UserService service;
    
    @Inject
    MatchmakingService matchmakingService;

    @POST
    public Response addUser(UserDto dto) {
        return Response.status(Status.CREATED).entity(service.addUser(dto)).build();
    }

    @PUT
    public Response updateUser(UserDto dto) {
        return Response.status(Status.OK).entity(service.updateUser(dto)).build();
    }

    @GET
    @Path("/{userId}")
    public Response getUser(@PathParam("userId") Long id) {
        return Response.ok(service.getUser(id)).build();
    }

    @GET
    public Response getUsers(@QueryParam("email") Optional<String> email) {
        return email
                .map(e -> Response.ok(service.getUser(e)).build())
                .orElseGet(() -> {
                    List<Users> response = service.getUsers();
                    return response.isEmpty() ? Response.status(Status.NO_CONTENT).build()
                            : Response.ok(response).build();
                });
    }

    @DELETE
    @Path("/{userId}")
    public Response deleteUser(@PathParam("userId") Long id) {
        boolean removed = service.removeUser(id);

        if (removed) {
            return Response.ok().build();
        } else {
            return Response.status(Status.BAD_REQUEST).build();
        }
    }

    @POST
    @Path("/{userId}/movies")
    public Response addMovies(@PathParam("userId") Long userId, AddMoviesDto addMoviesDto) {
        service.addUserMovies(userId, addMoviesDto);
        return Response.ok().build();
    }

    @GET
    @Path("/{userId}/pairs")
    public Response listUserCompatiblePairs(@PathParam("userId") Long userId) {
        return Response.ok().entity(matchmakingService.listCompatiblePairs(userId)).build();
    }

    @Inject
    MovieService movieService;

    @GET
    @Path("/teste")
    public List<PanacheEntityBase> getMovie() {
        return Movie.listAll();
    }

}
