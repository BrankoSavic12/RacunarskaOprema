/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.fink.dao;

/**
 *
 * @author MyPC
 */

import rs.ac.fink.data.Purchase;
import rs.ac.fink.data.User;
import rs.ac.fink.data.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PurchaseDao {

    private static final PurchaseDao instance = new PurchaseDao();

    private PurchaseDao() {}

    public static PurchaseDao getInstance() {
        return instance;
    }

    public Purchase find(int idPurchase, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Purchase purchase = null;
        try {
            ps = con.prepareStatement("SELECT * FROM purchase WHERE id_purchase=?");
            ps.setInt(1, idPurchase);
            rs = ps.executeQuery();
            if (rs.next()) {
                User user = UserDao.getInstance().find(rs.getInt("fk_user"), con);
                Product product = ProductDao.getInstance().find(rs.getInt("fk_product"), con);

                purchase = new Purchase(
                        rs.getInt("id_purchase"),
                        user,
                        product
                );
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return purchase;
    }


    public List<Purchase> findAll(Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Purchase> purchases = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM purchase");
            rs = ps.executeQuery();
            while (rs.next()) {
                User user = UserDao.getInstance().find(rs.getInt("fk_user"), con);
                Product product = ProductDao.getInstance().find(rs.getInt("fk_product"), con);

                purchases.add(new Purchase(
                        rs.getInt("id_purchase"),
                        user,
                        product
                ));
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return purchases;
    }

public int insert(Purchase purchase, Connection con) throws SQLException {
    PreparedStatement ps = null;
    try {
        ps = con.prepareStatement(
            "INSERT INTO purchase (id_purchase, fk_user, fk_product) VALUES (?, ?, ?)"
        );
        ps.setInt(1, purchase.getIdPurchase());
        ps.setInt(2, purchase.getUser().getIdUser());
        ps.setInt(3, purchase.getProduct().getIdProduct());

        ps.executeUpdate();
        return purchase.getIdPurchase();
    } finally {
        ResourcesManager.closeResources(null, ps);
    }
}


    public void update(Purchase purchase, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(
                    "UPDATE purchase SET fk_user=?, fk_product=? WHERE id_purchase=?"
            );
            ps.setInt(1, purchase.getUser().getIdUser());
            ps.setInt(2, purchase.getProduct().getIdProduct());
            ps.setInt(3, purchase.getIdPurchase());
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }

    public void delete(int idPurchase, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("DELETE FROM purchase WHERE id_purchase=?");
            ps.setInt(1, idPurchase);
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }
}
