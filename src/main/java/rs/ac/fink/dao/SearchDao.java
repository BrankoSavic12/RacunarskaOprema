/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.fink.dao;

/**
 *
 * @author MyPC
 */


import rs.ac.fink.data.Search;
import rs.ac.fink.data.User;
import rs.ac.fink.data.SearchSettings;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SearchDao {

    private static final SearchDao instance = new SearchDao();

    private SearchDao() {}

    public static SearchDao getInstance() {
        return instance;
    }

    public Search find(int idSearch, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Search search = null;
        try {
            ps = con.prepareStatement("SELECT * FROM search WHERE id_search=?");
            ps.setInt(1, idSearch);
            rs = ps.executeQuery();
            if (rs.next()) {
                User user = UserDao.getInstance().find(rs.getInt("fk_user"), con);
                SearchSettings settings = SearchSettingsDao.getInstance().find(rs.getInt("fk_search_settings"), con);

                search = new Search(
                        rs.getInt("id_search"),
                        user,
                        settings
                );
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return search;
    }

    public List<Search> findAll(Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Search> searches = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM search");
            rs = ps.executeQuery();
            while (rs.next()) {
                User user = UserDao.getInstance().find(rs.getInt("fk_user"), con);
                SearchSettings settings = SearchSettingsDao.getInstance().find(rs.getInt("fk_search_settings"), con);

                searches.add(new Search(
                        rs.getInt("id_search"),
                        user,
                        settings
                ));
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return searches;
    }

public int insert(Search search, Connection con) throws SQLException {
    PreparedStatement ps = null;
    try {
        ps = con.prepareStatement(
            "INSERT INTO search (id_search, fk_user, fk_search_settings) VALUES (?, ?, ?)"
        );

        ps.setInt(1, search.getIdSearch()); // ručno ID
        ps.setInt(2, search.getUser().getIdUser());
        ps.setInt(3, search.getSearchSettings().getIdSearchSettings());

        System.out.println("Executing query: INSERT INTO search (id_search, fk_user, fk_search_settings) VALUES (" 
            + search.getIdSearch() + ", " + search.getUser().getIdUser() + ", " 
            + search.getSearchSettings().getIdSearchSettings() + ")");

        ps.executeUpdate();

        return search.getIdSearch(); // vraća isti ID
    } finally {
        ResourcesManager.closeResources(null, ps);
    }
}


    public void update(Search search, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(
                    "UPDATE search SET fk_user=?, fk_search_settings=? WHERE id_search=?"
            );
            ps.setInt(1, search.getUser().getIdUser());
            ps.setInt(2, search.getSearchSettings().getIdSearchSettings());
            ps.setInt(3, search.getIdSearch());
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }

    public void delete(int idSearch, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("DELETE FROM search WHERE id_search=?");
            ps.setInt(1, idSearch);
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }
}
