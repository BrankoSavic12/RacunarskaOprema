/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.fink.rest;

/**
 *
 * @author MyPC
 */


import rs.ac.fink.data.Purchase;
import rs.ac.fink.exception.RacunarskaOpremaException;
import rs.ac.fink.service.PurchaseService;
import rs.ac.fink.dao.ResourcesManager;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.util.List;

@Path("/purchases")
public class PurchaseRest {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPurchases() {
        try (Connection con = ResourcesManager.getConnection()) {
            List<Purchase> purchases = PurchaseService.getInstance().getAllPurchases(con);
            return Response.ok(purchases).build();
        } catch (RacunarskaOpremaException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An unexpected error occurred.").build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPurchaseById(@PathParam("id") int id) {
        try (Connection con = ResourcesManager.getConnection()) {
            Purchase purchase = PurchaseService.getInstance().getPurchaseById(id, con);
            return Response.ok(purchase).build();
        } catch (RacunarskaOpremaException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An unexpected error occurred.").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPurchase(Purchase purchase) {
        try (Connection con = ResourcesManager.getConnection()) {
            int newId = PurchaseService.getInstance().addPurchase(purchase, con);
            return Response.status(Response.Status.CREATED).entity("Purchase created with ID " + newId).build();
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
    public Response updatePurchase(@PathParam("id") int id, Purchase purchase) {
        try (Connection con = ResourcesManager.getConnection()) {
            purchase.setIdPurchase(id);
            PurchaseService.getInstance().updatePurchase(purchase, con);
            return Response.ok("Purchase with ID " + id + " updated.").build();
        } catch (RacunarskaOpremaException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An unexpected error occurred.").build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePurchase(@PathParam("id") int id) {
        try (Connection con = ResourcesManager.getConnection()) {
            PurchaseService.getInstance().deletePurchase(id, con);
            return Response.ok("Purchase with ID " + id + " deleted.").build();
        } catch (RacunarskaOpremaException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An unexpected error occurred.").build();
        }
    }
}
