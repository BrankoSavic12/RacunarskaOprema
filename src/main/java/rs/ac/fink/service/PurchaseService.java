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

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PurchaseService {
    private static final PurchaseService instance = new PurchaseService();

    private PurchaseService() {}

    public static PurchaseService getInstance() {
        return instance;
    }

    public Purchase getPurchaseById(int idPurchase, Connection con) throws RacunarskaOpremaException {
        try {
            Purchase purchase = PurchaseDao.getInstance().find(idPurchase, con);
            if (purchase == null) {
                throw new RacunarskaOpremaException("Purchase with ID " + idPurchase + " not found.");
            }
            return purchase;
        } catch (SQLException e) {
            throw new RacunarskaOpremaException("Failed to retrieve purchase with ID " + idPurchase, e);
        }
    }

    public List<Purchase> getAllPurchases(Connection con) throws RacunarskaOpremaException {
        try {
            return PurchaseDao.getInstance().findAll(con);
        } catch (SQLException e) {
            throw new RacunarskaOpremaException("Failed to retrieve all purchases.", e);
        }
    }

    public int addPurchase(Purchase purchase, Connection con) throws RacunarskaOpremaException {
        try {
            return PurchaseDao.getInstance().insert(purchase, con);
        } catch (SQLException e) {
            throw new RacunarskaOpremaException("Failed to add purchase.", e);
        }
    }

    public void updatePurchase(Purchase purchase, Connection con) throws RacunarskaOpremaException {
        try {
            PurchaseDao.getInstance().update(purchase, con);
        } catch (SQLException e) {
            throw new RacunarskaOpremaException("Failed to update purchase with ID " + purchase.getIdPurchase(), e);
        }
    }

    public void deletePurchase(int idPurchase, Connection con) throws RacunarskaOpremaException {
        try {
            PurchaseDao.getInstance().delete(idPurchase, con);
        } catch (SQLException e) {
            throw new RacunarskaOpremaException("Failed to delete purchase with ID " + idPurchase, e);
        }
    }
}
