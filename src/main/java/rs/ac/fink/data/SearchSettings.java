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
public class SearchSettings implements Serializable {
    private int idSearchSettings;
    private long minPrice;
    private long maxPrice;
    private String type;
    private String keyword;

    public SearchSettings() {}

    public SearchSettings(int idSearchSettings, long minPrice, long maxPrice, String type, String keyword) {
        this.idSearchSettings = idSearchSettings;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.type = type;
        this.keyword = keyword;
    }

    public int getIdSearchSettings() {
        return idSearchSettings;
    }

    public void setIdSearchSettings(int idSearchSettings) {
        this.idSearchSettings = idSearchSettings;
    }

    public long getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(long minPrice) {
        this.minPrice = minPrice;
    }

    public long getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(long maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String toString() {
        return "SearchSettings{" +
                "idSearchSettings=" + idSearchSettings +
                ", minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                ", type='" + type + '\'' +
                ", keyword='" + keyword + '\'' +
                '}';
    }
}