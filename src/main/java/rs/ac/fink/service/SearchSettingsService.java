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
import rs.ac.fink.dao.ResourcesManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SearchSettingsService {
    private static final SearchSettingsService instance = new SearchSettingsService();

    private SearchSettingsService() {}

    public static SearchSettingsService getInstance() {
        return instance;
    }

    public SearchSettings getSearchSettingsById(int idSearchSettings) throws RacunarskaOpremaException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            return SearchSettingsDao.getInstance().find(idSearchSettings, con);
        } catch (SQLException e) {
            throw new RacunarskaOpremaException("Failed to retrieve search settings with ID " + idSearchSettings, e);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public List<SearchSettings> getAllSearchSettings() throws RacunarskaOpremaException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            return SearchSettingsDao.getInstance().findAll(con);
        } catch (SQLException e) {
            throw new RacunarskaOpremaException("Failed to retrieve all search settings.", e);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public int addSearchSettings(SearchSettings searchSettings) throws RacunarskaOpremaException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            int searchSettingsId = SearchSettingsDao.getInstance().insert(searchSettings, con);

            con.commit();
            return searchSettingsId;
        } catch (SQLException e) {
            ResourcesManager.rollbackTransactions(con);
            throw new RacunarskaOpremaException("Failed to add search settings.", e);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public void updateSearchSettings(SearchSettings searchSettings) throws RacunarskaOpremaException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            SearchSettingsDao.getInstance().update(searchSettings, con);

            con.commit();
        } catch (SQLException e) {
            ResourcesManager.rollbackTransactions(con);
            throw new RacunarskaOpremaException("Failed to update search settings with ID " + searchSettings.getIdSearchSettings(), e);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public void deleteSearchSettings(int idSearchSettings) throws RacunarskaOpremaException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            SearchSettingsDao.getInstance().delete(idSearchSettings, con);

            con.commit();
        } catch (SQLException e) {
            ResourcesManager.rollbackTransactions(con);
            throw new RacunarskaOpremaException("Failed to delete search settings with ID " + idSearchSettings, e);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
}
