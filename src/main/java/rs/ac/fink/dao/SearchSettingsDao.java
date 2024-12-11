/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.fink.dao;

/**
 *
 * @author MyPC
 */
import rs.ac.fink.data.SearchSettings;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SearchSettingsDao {

    private static final SearchSettingsDao instance = new SearchSettingsDao();

    private SearchSettingsDao() {}

    public static SearchSettingsDao getInstance() {
        return instance;
    }

    public SearchSettings find(int idSearchSettings, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        SearchSettings settings = null;
        try {
            ps = con.prepareStatement("SELECT * FROM search_settings WHERE id_search_settings=?");
            ps.setInt(1, idSearchSettings);
            rs = ps.executeQuery();
            if (rs.next()) {
                settings = new SearchSettings(
                        rs.getInt("id_search_settings"),
                        rs.getLong("min_price"),
                        rs.getLong("max_price"),
                        rs.getString("type"),
                        rs.getString("keyword")
                );
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return settings;
    }

    public List<SearchSettings> findAll(Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<SearchSettings> settingsList = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM search_settings");
            rs = ps.executeQuery();
            while (rs.next()) {
                settingsList.add(new SearchSettings(
                        rs.getInt("id_search_settings"),
                        rs.getLong("min_price"),
                        rs.getLong("max_price"),
                        rs.getString("type"),
                        rs.getString("keyword")
                ));
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return settingsList;
    }

    public int insert(SearchSettings settings, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = -1;
        try {
            ps = con.prepareStatement(
                    "INSERT INTO search_settings (min_price, max_price, type, keyword) VALUES (?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            ps.setLong(1, settings.getMinPrice());
            ps.setLong(2, settings.getMaxPrice());
            ps.setString(3, settings.getType());
            ps.setString(4, settings.getKeyword());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return id;
    }

    public void update(SearchSettings settings, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(
                    "UPDATE search_settings SET min_price=?, max_price=?, type=?, keyword=? WHERE id_search_settings=?"
            );
            ps.setLong(1, settings.getMinPrice());
            ps.setLong(2, settings.getMaxPrice());
            ps.setString(3, settings.getType());
            ps.setString(4, settings.getKeyword());
            ps.setInt(5, settings.getIdSearchSettings());
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }

    public void delete(int idSearchSettings, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("DELETE FROM search_settings WHERE id_search_settings=?");
            ps.setInt(1, idSearchSettings);
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }
}
