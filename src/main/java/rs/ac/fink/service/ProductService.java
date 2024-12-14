/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.fink.service;

/**
 *
 * @author MyPC
 */


import rs.ac.fink.dao.ProductDao;
import rs.ac.fink.data.Product;
import rs.ac.fink.exception.RacunarskaOpremaException;
import rs.ac.fink.dao.ResourcesManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ProductService {
    private static final ProductService instance = new ProductService();

    private ProductService() {}

    public static ProductService getInstance() {
        return instance;
    }

    public Product getProductById(int idProduct) throws RacunarskaOpremaException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            return ProductDao.getInstance().find(idProduct, con);
        } catch (SQLException e) {
            throw new RacunarskaOpremaException("Failed to retrieve product with ID " + idProduct, e);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public List<Product> getAllProducts() throws RacunarskaOpremaException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            return ProductDao.getInstance().findAll(con);
        } catch (SQLException e) {
            throw new RacunarskaOpremaException("Failed to retrieve all products.", e);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public int addProduct(Product product) throws RacunarskaOpremaException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            int productId = ProductDao.getInstance().insert(product, con);

            con.commit();
            return productId;
        } catch (SQLException e) {
            ResourcesManager.rollbackTransactions(con);
            throw new RacunarskaOpremaException("Failed to add product.", e);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public void updateProduct(Product product) throws RacunarskaOpremaException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            ProductDao.getInstance().update(product, con);

            con.commit();
        } catch (SQLException e) {
            ResourcesManager.rollbackTransactions(con);
            throw new RacunarskaOpremaException("Failed to update product with ID " + product.getIdProduct(), e);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public void deleteProduct(int idProduct) throws RacunarskaOpremaException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            ProductDao.getInstance().delete(idProduct, con);

            con.commit();
        } catch (SQLException e) {
            ResourcesManager.rollbackTransactions(con);
            throw new RacunarskaOpremaException("Failed to delete product with ID " + idProduct, e);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
}

