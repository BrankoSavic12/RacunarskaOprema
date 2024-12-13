/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.fink.rest;

/**
 *
 * @author MyPC
 */

import rs.ac.fink.data.SearchSettings;
import rs.ac.fink.exception.RacunarskaOpremaException;
import rs.ac.fink.service.SearchSettingsService;
import rs.ac.fink.dao.ResourcesManager;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.util.List;

@Path("/search-settings")
public class SearchSettingsRest {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllSearchSettings() {
        try (Connection con = ResourcesManager.getConnection()) {
            List<SearchSettings> searchSettingsList = SearchSettingsService.getInstance().getAllSearchSettings(con);
            return Response.ok(searchSettingsList).build();
        } catch (RacunarskaOpremaException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An unexpected error occurred.").build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSearchSettingsById(@PathParam("id") int id) {
        try (Connection con = ResourcesManager.getConnection()) {
            SearchSettings searchSettings = SearchSettingsService.getInstance().getSearchSettingsById(id, con);
            return Response.ok(searchSettings).build();
        } catch (RacunarskaOpremaException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An unexpected error occurred.").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addSearchSettings(SearchSettings searchSettings) {
        try (Connection con = ResourcesManager.getConnection()) {
            int newId = SearchSettingsService.getInstance().addSearchSettings(searchSettings, con);
            return Response.status(Response.Status.CREATED).entity("SearchSettings created with ID " + newId).build();
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
    public Response updateSearchSettings(@PathParam("id") int id, SearchSettings searchSettings) {
        try (Connection con = ResourcesManager.getConnection()) {
            searchSettings.setIdSearchSettings(id);
            SearchSettingsService.getInstance().updateSearchSettings(searchSettings, con);
            return Response.ok("SearchSettings with ID " + id + " updated.").build();
        } catch (RacunarskaOpremaException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An unexpected error occurred.").build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteSearchSettings(@PathParam("id") int id) {
        try (Connection con = ResourcesManager.getConnection()) {
            SearchSettingsService.getInstance().deleteSearchSettings(id, con);
            return Response.ok("SearchSettings with ID " + id + " deleted.").build();
        } catch (RacunarskaOpremaException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An unexpected error occurred.").build();
        }
    }
}
