/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.fink.start;

/**
 *
 * @author MyPC
 */

import rs.ac.fink.data.*;
import rs.ac.fink.service.*;
import rs.ac.fink.exception.RacunarskaOpremaException;

public class RacunarskaOprema {
    public static void main(String[] args) {
        try {
            System.out.println("=== Pokretanje aplikacije ===");

            // Operacije za korisnike
            UserService userService = UserService.getInstance();
            System.out.println("\n=== Operacije sa korisnicima ===");

            User newUser = new User(0, "Jovan Jovanovic44", "jjovan", "jovan.jovanovic@example.com", "1988-03-12", 10000, 5000);
            int userId = userService.addUser(newUser);
            System.out.println("Dodat korisnik sa ID: " + userId);

            User retrievedUser = userService.getUserById(userId);
            System.out.println("Preuzet korisnik: " + retrievedUser);

            // Brisanje korisnika
            System.out.println("Brisanje korisnika sa ID: " + userId);
            userService.deleteUser(userId);
            System.out.println("Korisnik obrisan.");

            // Operacije za proizvode
            ProductService productService = ProductService.getInstance();
            System.out.println("\n=== Operacije sa proizvodima ===");

            Product newProduct = new Product(0, "Grafička karta", 1500, "Computer Hardware", 5);
            int productId = productService.addProduct(newProduct);
            System.out.println("Dodat proizvod sa ID: " + productId);

            Product retrievedProduct = productService.getProductById(productId);
            System.out.println("Preuzet proizvod: " + retrievedProduct);

            // Brisanje proizvoda
            System.out.println("Brisanje proizvoda sa ID: " + productId);
            productService.deleteProduct(productId);
            System.out.println("Proizvod obrisan.");

            // Operacije za kupovine
            PurchaseService purchaseService = PurchaseService.getInstance();
            System.out.println("\n=== Operacije sa kupovinama ===");

            Purchase newPurchase = new Purchase(retrievedUser, retrievedProduct);
            int purchaseId = purchaseService.addPurchase(newPurchase);
            System.out.println("Dodata kupovina sa ID: " + purchaseId);

        } catch (RacunarskaOpremaException e) {
            System.err.println("Greška u aplikaciji: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Neočekivana greška: " + e.getMessage());
        }
    }
}

