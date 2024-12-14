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

        // Provera da li korisnik ima dovoljno sredstava
        User user = UserDao.getInstance().find(purchase.getUser().getIdUser(), con);
        Product product = ProductDao.getInstance().find(purchase.getProduct().getIdProduct(), con);

        if (user.getAccountBalance() < product.getPrice()) {
            throw new RacunarskaOpremaException("Korisnik nema dovoljno sredstava za kupovinu.");
        }

        // Umanji iznos na računu korisnika
        long newBalance = user.getAccountBalance() - product.getPrice();
        user.setAccountBalance(newBalance);
        UserDao.getInstance().update(user, con);

        // Kreiraj kupovinu
        int purchaseId = PurchaseDao.getInstance().insert(purchase, con);
        if (true) { // Ovo će uvek izazvati grešku za test
            throw new SQLException("Simulirana greška tokom transakcije!");
        }
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
