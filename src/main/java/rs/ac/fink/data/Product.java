/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.fink.data;

/**
 *
 * @author MyPC
 */

import java.io.Serializable;

// Klasa za reprezentaciju proizvoda
public class Product implements Serializable {
    private int idProduct;
    private String name;
    private long price;
    private String type;
    private long stockQuantity;

    public Product() {}

    public Product(int idProduct, String name, long price, String type, long stockQuantity) {
        this.idProduct = idProduct;
        this.name = name;
        this.price = price;
        this.type = type;
        this.stockQuantity = stockQuantity;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(long stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "idProduct=" + idProduct +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", type='" + type + '\'' +
                ", stockQuantity=" + stockQuantity +
                '}';
    }
}