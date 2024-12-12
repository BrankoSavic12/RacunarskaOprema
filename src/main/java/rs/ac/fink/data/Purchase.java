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

public class Purchase implements Serializable {
    private int idPurchase;
    private User user;        // Objekat User
    private Product product;  // Objekat Product

    public Purchase() {}

    public Purchase(int idPurchase, User user, Product product) {
        this.idPurchase = idPurchase;
        this.user = user;
        this.product = product;
    }

    public Purchase(User user, Product product) {
        this.user = user;
        this.product = product;
    }

    public int getIdPurchase() {
        return idPurchase;
    }

    public void setIdPurchase(int idPurchase) {
        this.idPurchase = idPurchase;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "idPurchase=" + idPurchase +
                ", user=" + user +
                ", product=" + product +
                '}';
    }
}
