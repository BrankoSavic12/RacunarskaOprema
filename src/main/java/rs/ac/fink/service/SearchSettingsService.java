/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.fink.service;

/**
 *
 * @author MyPC
 */

import rs.ac.fink.dao.SearchSettingsDao;
import rs.ac.fink.data.SearchSettings;
import rs.ac.fink.exception.RacunarskaOpremaException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SearchSettingsService {
    private static final SearchSettingsService instance = new SearchSettingsService();

    private SearchSettingsService() {}

    public static SearchSettingsService getInstance() {
        return instance;
    }

    public SearchSettings getSearchSettingsById(int idSearchSettings, Connection con) throws RacunarskaOpremaException {
        try {
            SearchSettings searchSettings = SearchSettingsDao.getInstance().find(idSearchSettings, con);
            if (searchSettings == null) {
                throw new RacunarskaOpremaException("SearchSettings with ID " + idSearchSettings + " not found.");
            }
            return searchSettings;
        } catch (SQLException e) {
            throw new RacunarskaOpremaException("Failed to retrieve search settings with ID " + idSearchSettings, e);
        }
    }

    public List<SearchSettings> getAllSearchSettings(Connection con) throws RacunarskaOpremaException {
        try {
            return SearchSettingsDao.getInstance().findAll(con);
        } catch (SQLException e) {
            throw new RacunarskaOpremaException("Failed to retrieve all search settings.", e);
        }
    }

    public int addSearchSettings(SearchSettings searchSettings, Connection con) throws RacunarskaOpremaException {
        try {
            return SearchSettingsDao.getInstance().insert(searchSettings, con);
        } catch (SQLException e) {
            throw new RacunarskaOpremaException("Failed to add search settings.", e);
        }
    }

    public void updateSearchSettings(SearchSettings searchSettings, Connection con) throws RacunarskaOpremaException {
        try {
            SearchSettingsDao.getInstance().update(searchSettings, con);
        } catch (SQLException e) {
            throw new RacunarskaOpremaException("Failed to update search settings with ID " + searchSettings.getIdSearchSettings(), e);
        }
    }

    public void deleteSearchSettings(int idSearchSettings, Connection con) throws RacunarskaOpremaException {
        try {
            SearchSettingsDao.getInstance().delete(idSearchSettings, con);
        } catch (SQLException e) {
            throw new RacunarskaOpremaException("Failed to delete search settings with ID " + idSearchSettings, e);
        }
    }
}
