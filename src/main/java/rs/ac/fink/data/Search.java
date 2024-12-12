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
    private User user;
    private SearchSettings searchSettings;

    public Search() {}

    public Search(int idSearch, User user, SearchSettings searchSettings) {
        this.idSearch = idSearch;
        this.user = user;
        this.searchSettings = searchSettings;
    }

    public Search(User user, SearchSettings searchSettings) {
        this.user = user;
        this.searchSettings = searchSettings;
    }

    public int getIdSearch() {
        return idSearch;
    }

    public void setIdSearch(int idSearch) {
        this.idSearch = idSearch;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public SearchSettings getSearchSettings() {
        return searchSettings;
    }

    public void setSearchSettings(SearchSettings searchSettings) {
        this.searchSettings = searchSettings;
    }

    @Override
    public String toString() {
        return "Search{" +
                "idSearch=" + idSearch +
                ", user=" + user +
                ", searchSettings=" + searchSettings +
                '}';
    }
}
