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
    private int userId;
    private int productId;

    public Purchase() {}

    public Purchase(int idPurchase, int userId, int productId) {
        this.idPurchase = idPurchase;
        this.userId = userId;
        this.productId = productId;
    }

    public int getIdPurchase() {
        return idPurchase;
    }

    public void setIdPurchase(int idPurchase) {
        this.idPurchase = idPurchase;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "idPurchase=" + idPurchase +
                ", userId=" + userId +
                ", productId=" + productId +
                '}';
    }
}
