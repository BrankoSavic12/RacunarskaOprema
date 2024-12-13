/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.fink.rest;

/**
 *
 * @author MyPC
 */

import rs.ac.fink.data.Search;
import rs.ac.fink.exception.RacunarskaOpremaException;
import rs.ac.fink.service.SearchService;
import rs.ac.fink.dao.ResourcesManager;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.util.List;

@Path("/searches")
public class SearchRest {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllSearches() {
        try (Connection con = ResourcesManager.getConnection()) {
            List<Search> searches = SearchService.getInstance().getAllSearches(con);
            return Response.ok(searches).build();
        } catch (RacunarskaOpremaException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An unexpected error occurred.").build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSearchById(@PathParam("id") int id) {
        try (Connection con = ResourcesManager.getConnection()) {
            Search search = SearchService.getInstance().getSearchById(id, con);
            return Response.ok(search).build();
        } catch (RacunarskaOpremaException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An unexpected error occurred.").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addSearch(Search search) {
        try (Connection con = ResourcesManager.getConnection()) {
            int newId = SearchService.getInstance().addSearch(search, con);
            return Response.status(Response.Status.CREATED).entity("Search created with ID " + newId).build();
        } catch (RacunarskaOpremaException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An unexpected error occurred.").build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateSearch(@PathParam("id") int id, Search search) {
        try (Connection con = ResourcesManager.getConnection()) {
            search.setIdSearch(id);
            SearchService.getInstance().updateSearch(search, con);
            return Response.ok("Search with ID " + id + " updated.").build();
        } catch (RacunarskaOpremaException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An unexpected error occurred.").build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteSearch(@PathParam("id") int id) {
        try (Connection con = ResourcesManager.getConnection()) {
            SearchService.getInstance().deleteSearch(id, con);
            return Response.ok("Search with ID " + id + " deleted.").build();
        } catch (RacunarskaOpremaException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An unexpected error occurred.").build();
        }
    }
}
