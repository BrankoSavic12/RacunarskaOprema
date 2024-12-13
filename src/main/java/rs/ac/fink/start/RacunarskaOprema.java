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
import rs.ac.fink.dao.ResourcesManager;
import rs.ac.fink.exception.RacunarskaOpremaException;

import java.sql.Connection;

public class RacunarskaOprema {
    public static void main(String[] args) {
        try (Connection con = ResourcesManager.getConnection()) {
            System.out.println("Konekcija sa bazom podataka uspesno uspostavljena.");

            // Operacije za korisnike
            UserService userService = UserService.getInstance();
            System.out.println("\n=== Operacije sa korisnicima ===");

            User newUser = new User(0, "Ana Ivanovic", "ana", "ana@example.com", "1990-01-01", 9000, 17000);
            int userId = userService.addUser(newUser, con);
            User retrievedUser = userService.getUserById(userId, con);

            // Operacije za proizvode
            ProductService productService = ProductService.getInstance();
            System.out.println("\n=== Operacije sa proizvodima ===");

            Product newProduct = new Product(0, "Kompjuter", 12000, "Electronics", 20);
            int productId = productService.addProduct(newProduct, con);
            Product retrievedProduct = productService.getProductById(productId, con);

            // Operacije za kupovine
            PurchaseService purchaseService = PurchaseService.getInstance();
            System.out.println("\n=== Operacije sa kupovinama ===");

            Purchase newPurchase = new Purchase(retrievedUser, retrievedProduct);
            int purchaseId = purchaseService.addPurchase(newPurchase, con);

            // Operacije za SearchSettings
            SearchSettingsService searchSettingsService = SearchSettingsService.getInstance();
            System.out.println("\n=== Operacije sa SearchSettings ===");

            SearchSettings newSearchSettings = new SearchSettings(0, 1000, 2000, "Kompjuter", "Strimovanje");
            int searchSettingsId = searchSettingsService.addSearchSettings(newSearchSettings, con);
            SearchSettings retrievedSearchSettings = searchSettingsService.getSearchSettingsById(searchSettingsId, con);

            // Operacije za Search
            SearchService searchService = SearchService.getInstance();
            System.out.println("\n=== Operacije sa Search ===");

            
            try {
                Search newSearch = new Search(0, retrievedUser, retrievedSearchSettings);
                int searchId = searchService.addSearch(newSearch, con);
                System.out.println("Dodat Search sa ID: " + searchId);

                // Preuzimanje Search
                Search retrievedSearch = searchService.getSearchById(searchId, con);
                System.out.println("Preuzet Search: " + retrievedSearch);
            } catch (Exception e) {
                System.err.println("Greska prilikom operacija sa Search: " + e.getMessage());
            }

        } catch (RacunarskaOpremaException e) {
            System.err.println("Greska u aplikaciji: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Neocekivana greška: " + e.getMessage());
        }
    }
}
