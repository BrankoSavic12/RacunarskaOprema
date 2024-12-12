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

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ProductService {
    private static final ProductService instance = new ProductService();

    private ProductService() {}

    public static ProductService getInstance() {
        return instance;
    }

    public Product getProductById(int idProduct, Connection con) throws RacunarskaOpremaException {
        try {
            Product product = ProductDao.getInstance().find(idProduct, con);
            if (product == null) {
                throw new RacunarskaOpremaException("Product with ID " + idProduct + " not found.");
            }
            return product;
        } catch (SQLException e) {
            throw new RacunarskaOpremaException("Failed to retrieve product with ID " + idProduct, e);
        }
    }

    public List<Product> getAllProducts(Connection con) throws RacunarskaOpremaException {
        try {
            return ProductDao.getInstance().findAll(con);
        } catch (SQLException e) {
            throw new RacunarskaOpremaException("Failed to retrieve all products.", e);
        }
    }

    public int addProduct(Product product, Connection con) throws RacunarskaOpremaException {
        try {
            return ProductDao.getInstance().insert(product, con);
        } catch (SQLException e) {
            throw new RacunarskaOpremaException("Failed to add product.", e);
        }
    }

    public void updateProduct(Product product, Connection con) throws RacunarskaOpremaException {
        try {
            ProductDao.getInstance().update(product, con);
        } catch (SQLException e) {
            throw new RacunarskaOpremaException("Failed to update product with ID " + product.getIdProduct(), e);
        }
    }

    public void deleteProduct(int idProduct, Connection con) throws RacunarskaOpremaException {
        try {
            ProductDao.getInstance().delete(idProduct, con);
        } catch (SQLException e) {
            throw new RacunarskaOpremaException("Failed to delete product with ID " + idProduct, e);
        }
    }
}
