/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.fink.service;

/**
 *
 * @author MyPC
 */

import rs.ac.fink.dao.PurchaseDao;
import rs.ac.fink.data.Purchase;
import rs.ac.fink.exception.RacunarskaOpremaException;
import rs.ac.fink.dao.ResourcesManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import rs.ac.fink.dao.ProductDao;
import rs.ac.fink.dao.UserDao;
import rs.ac.fink.data.Product;
import rs.ac.fink.data.User;

public class PurchaseService {
    private static final PurchaseService instance = new PurchaseService();

    private PurchaseService() {}

    public static PurchaseService getInstance() {
        return instance;
    }

    public Purchase getPurchaseById(int idPurchase) throws RacunarskaOpremaException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            return PurchaseDao.getInstance().find(idPurchase, con);
        } catch (SQLException e) {
            throw new RacunarskaOpremaException("Failed to retrieve purchase with ID " + idPurchase, e);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public List<Purchase> getAllPurchases() throws RacunarskaOpremaException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            return PurchaseDao.getInstance().findAll(con);
        } catch (SQLException e) {
            throw new RacunarskaOpremaException("Failed to retrieve all purchases.", e);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

public int addPurchase(Purchase purchase) throws RacunarskaOpremaException {
    Connection con = null;
    try {
        con = ResourcesManager.getConnection();
        con.setAutoCommit(false);

        // Provera korisnika i proizvoda
        User user = UserDao.getInstance().find(purchase.getUser().getIdUser(), con);
        Product product = ProductDao.getInstance().find(purchase.getProduct().getIdProduct(), con);

        if (user.getAccountBalance() < product.getPrice()) {
            throw new RacunarskaOpremaException("Korisnik nema dovoljno sredstava za kupovinu.");
        }

        if (product.getStockQuantity() <= 0) {
            throw new RacunarskaOpremaException("Proizvod je trenutno rasprodat.");
        }

        // Ažuriraj korisnika
        long newBalance = user.getAccountBalance() - product.getPrice();
        long newSpentAmount = user.getSpentAmount() + product.getPrice();

        user.setAccountBalance(newBalance);
        user.setSpentAmount(newSpentAmount);

        UserDao.getInstance().update(user, con);

        // Ažuriraj proizvod
        long newStockQuantity = product.getStockQuantity() - 1;
        product.setStockQuantity(newStockQuantity);
        ProductDao.getInstance().update(product, con);

        // Kreiraj kupovinu
        int purchaseId = PurchaseDao.getInstance().insert(purchase, con);

        // Commit
        con.commit();
        return purchaseId;

    } catch (SQLException e) {
        ResourcesManager.rollbackTransactions(con);
        throw new RacunarskaOpremaException("Kupovina nije uspešno izvršena.", e);
    } finally {
        ResourcesManager.closeConnection(con);
    }
}


public void updatePurchase(Purchase purchase) throws RacunarskaOpremaException {
    Connection con = null;
    try {
        con = ResourcesManager.getConnection();
        con.setAutoCommit(false);

        // 1. Stara kupovina
        Purchase oldPurchase = PurchaseDao.getInstance().find(purchase.getIdPurchase(), con);
        if (oldPurchase == null) {
            throw new RacunarskaOpremaException("Kupovina sa ID " + purchase.getIdPurchase() + " ne postoji.");
        }

        User oldUser = UserDao.getInstance().find(oldPurchase.getUser().getIdUser(), con);
        Product oldProduct = ProductDao.getInstance().find(oldPurchase.getProduct().getIdProduct(), con);

        // 2. Vrati starom korisniku stanje samo ako se menja korisnik ili proizvod
        if (oldUser.getIdUser() != purchase.getUser().getIdUser() ||
            oldProduct.getIdProduct() != purchase.getProduct().getIdProduct()) {

            oldUser.setAccountBalance(oldUser.getAccountBalance() + oldProduct.getPrice());
            oldUser.setSpentAmount(oldUser.getSpentAmount() - oldProduct.getPrice());
            UserDao.getInstance().update(oldUser, con);

            oldProduct.setStockQuantity(oldProduct.getStockQuantity() + 1);
            ProductDao.getInstance().update(oldProduct, con);
        }

        // 3. Novi korisnik i proizvod
        User newUser = UserDao.getInstance().find(purchase.getUser().getIdUser(), con);
        Product newProduct = ProductDao.getInstance().find(purchase.getProduct().getIdProduct(), con);

        if (newUser.getAccountBalance() < newProduct.getPrice()) {
            throw new RacunarskaOpremaException("Novi korisnik nema dovoljno sredstava.");
        }
        if (newProduct.getStockQuantity() <= 0) {
            throw new RacunarskaOpremaException("Novi proizvod je rasprodat.");
        }

        // 4. Skidanje sa novog korisnika
        newUser.setAccountBalance(newUser.getAccountBalance() - newProduct.getPrice());
        newUser.setSpentAmount(newUser.getSpentAmount() + newProduct.getPrice());
        UserDao.getInstance().update(newUser, con);

        newProduct.setStockQuantity(newProduct.getStockQuantity() - 1);
        ProductDao.getInstance().update(newProduct, con);

        // 5. Update same kupovine
        PurchaseDao.getInstance().update(purchase, con);

        con.commit();
    } catch (SQLException e) {
        ResourcesManager.rollbackTransactions(con);
        throw new RacunarskaOpremaException("Failed to update purchase with ID " + purchase.getIdPurchase(), e);
    } finally {
        ResourcesManager.closeConnection(con);
    }
}


    public void deletePurchase(int idPurchase) throws RacunarskaOpremaException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            PurchaseDao.getInstance().delete(idPurchase, con);

            con.commit();
        } catch (SQLException e) {
            ResourcesManager.rollbackTransactions(con);
            throw new RacunarskaOpremaException("Failed to delete purchase with ID " + idPurchase, e);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
}
