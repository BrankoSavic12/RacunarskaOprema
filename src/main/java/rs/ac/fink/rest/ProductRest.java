/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.fink.rest;

/**
 *
 * @author MyPC
 */



import rs.ac.fink.data.Product;
import rs.ac.fink.exception.RacunarskaOpremaException;
import rs.ac.fink.service.ProductService;
import rs.ac.fink.dao.ResourcesManager;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.util.List;

@Path("/products")
public class ProductRest {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProducts() {
        try (Connection con = ResourcesManager.getConnection()) {
            System.out.println("Fetching all products...");
            List<Product> products = ProductService.getInstance().getAllProducts(con);
            return Response.ok(products).build();
        } catch (RacunarskaOpremaException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An unexpected error occurred.").build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductById(@PathParam("id") int id) {
        try (Connection con = ResourcesManager.getConnection()) {
            System.out.println("Fetching product with ID: " + id);
            Product product = ProductService.getInstance().getProductById(id, con);
            return Response.ok(product).build();
        } catch (RacunarskaOpremaException e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An unexpected error occurred.").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addProduct(Product product) {
        try (Connection con = ResourcesManager.getConnection()) {
            System.out.println("Adding product: " + product);
            int newId = ProductService.getInstance().addProduct(product, con);
            System.out.println("Product added with ID: " + newId);
            return Response.status(Response.Status.CREATED).entity("Product created with ID " + newId).build();
        } catch (RacunarskaOpremaException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An unexpected error occurred.").build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProduct(@PathParam("id") int id, Product product) {
        try (Connection con = ResourcesManager.getConnection()) {
            product.setIdProduct(id);
            System.out.println("Updating product: " + product);
            ProductService.getInstance().updateProduct(product, con);
            System.out.println("Product updated with ID: " + id);
            return Response.ok("Product with ID " + id + " updated.").build();
        } catch (RacunarskaOpremaException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An unexpected error occurred.").build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProduct(@PathParam("id") int id) {
        try (Connection con = ResourcesManager.getConnection()) {
            System.out.println("Deleting product with ID: " + id);
            ProductService.getInstance().deleteProduct(id, con);
            System.out.println("Product deleted with ID: " + id);
            return Response.ok("Product with ID " + id + " deleted.").build();
        } catch (RacunarskaOpremaException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An unexpected error occurred.").build();
        }
    }
}
