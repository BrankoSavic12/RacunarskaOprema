/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.fink.dao;

/**
 *
 * @author MyPC
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import rs.ac.fink.data.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {

    private static final ProductDao instance = new ProductDao();

    private ProductDao() {}

    public static ProductDao getInstance() {
        return instance;
    }

    public Product find(int idProduct, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Product product = null;
        try {
            ps = con.prepareStatement("SELECT * FROM product WHERE id_product=?");
            ps.setInt(1, idProduct);
            rs = ps.executeQuery();
            if (rs.next()) {
                product = new Product(
                        rs.getInt("id_product"),
                        rs.getString("name"),
                        rs.getLong("price"),
                        rs.getString("type"),
                        rs.getLong("stock_quantity")
                );
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return product;
    }

    public List<Product> findAll(Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Product> products = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM product");
            rs = ps.executeQuery();
            while (rs.next()) {
                products.add(new Product(
                        rs.getInt("id_product"),
                        rs.getString("name"),
                        rs.getLong("price"),
                        rs.getString("type"),
                        rs.getLong("stock_quantity")
                ));
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return products;
    }

    public int insert(Product product, Connection con) throws SQLException {
    PreparedStatement ps = null;
    try {
        ps = con.prepareStatement(
            "INSERT INTO product (id_product, name, price, type, stock_quantity) VALUES (?, ?, ?, ?, ?)"
        );
        ps.setInt(1, product.getIdProduct());
        ps.setString(2, product.getName());
        ps.setLong(3, product.getPrice());
        ps.setString(4, product.getType());
        ps.setLong(5, product.getStockQuantity());

        ps.executeUpdate();
        return product.getIdProduct(); 
    } finally {
        ResourcesManager.closeResources(null, ps);
    }
}


    public void update(Product product, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(
                    "UPDATE product SET name=?, price=?, type=?, stock_quantity=? WHERE id_product=?"
            );
            ps.setString(1, product.getName());
            ps.setLong(2, product.getPrice());
            ps.setString(3, product.getType());
            ps.setLong(4, product.getStockQuantity());
            ps.setInt(5, product.getIdProduct());
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }

    public void delete(int idProduct, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("DELETE FROM product WHERE id_product=?");
            ps.setInt(1, idProduct);
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }
}
