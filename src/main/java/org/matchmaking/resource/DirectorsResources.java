package org.matchmaking.resource;

import org.matchmaking.dto.MovieDirectorDto;
import org.matchmaking.service.DirectorService;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/directors")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DirectorsResources {

    @Inject
    DirectorService service;

    @POST
    public Response createDirector(MovieDirectorDto dto) {
        return Response.status(Status.CREATED).entity(service.createDirector(dto)).build();
    }

    @GET
    public Response getDirectors() {
        return Response.ok(service.listDirectors()).build();
    }

    @DELETE
    @Path("/{directorId}")
    public Response removeDirector(@PathParam("directorId") Long id) {
        boolean removed = service.removeDirector(id);
        if(removed) {
            return Response.ok().build();
        } else {
            return Response.status(Status.BAD_REQUEST).build();
        }
    } 

}
