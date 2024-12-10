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
public class Search implements Serializable {
    private int idSearch;
    private int userId;
    private int searchSettingsId;

    public Search() {}

    public Search(int idSearch, int userId, int searchSettingsId) {
        this.idSearch = idSearch;
        this.userId = userId;
        this.searchSettingsId = searchSettingsId;
    }

    public int getIdSearch() {
        return idSearch;
    }

    public void setIdSearch(int idSearch) {
        this.idSearch = idSearch;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSearchSettingsId() {
        return searchSettingsId;
    }

    public void setSearchSettingsId(int searchSettingsId) {
        this.searchSettingsId = searchSettingsId;
    }

    @Override
    public String toString() {
        return "Search{" +
                "idSearch=" + idSearch +
                ", userId=" + userId +
                ", searchSettingsId=" + searchSettingsId +
                '}';
    }
}