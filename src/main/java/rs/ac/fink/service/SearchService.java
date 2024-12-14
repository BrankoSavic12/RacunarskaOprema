/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.fink.service;

/**
 *
 * @author MyPC
 */

import rs.ac.fink.dao.SearchDao;
import rs.ac.fink.data.Search;
import rs.ac.fink.exception.RacunarskaOpremaException;
import rs.ac.fink.dao.ResourcesManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SearchService {
    private static final SearchService instance = new SearchService();

    private SearchService() {}

    public static SearchService getInstance() {
        return instance;
    }

    public Search getSearchById(int idSearch) throws RacunarskaOpremaException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            return SearchDao.getInstance().find(idSearch, con);
        } catch (SQLException e) {
            throw new RacunarskaOpremaException("Failed to retrieve search with ID " + idSearch, e);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public List<Search> getAllSearches() throws RacunarskaOpremaException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            return SearchDao.getInstance().findAll(con);
        } catch (SQLException e) {
            throw new RacunarskaOpremaException("Failed to retrieve all searches.", e);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public int addSearch(Search search) throws RacunarskaOpremaException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            int searchId = SearchDao.getInstance().insert(search, con);

            con.commit();
            return searchId;
        } catch (SQLException e) {
            ResourcesManager.rollbackTransactions(con);
            throw new RacunarskaOpremaException("Failed to add search.", e);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public void updateSearch(Search search) throws RacunarskaOpremaException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            SearchDao.getInstance().update(search, con);

            con.commit();
        } catch (SQLException e) {
            ResourcesManager.rollbackTransactions(con);
            throw new RacunarskaOpremaException("Failed to update search with ID " + search.getIdSearch(), e);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public void deleteSearch(int idSearch) throws RacunarskaOpremaException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            SearchDao.getInstance().delete(idSearch, con);

            con.commit();
        } catch (SQLException e) {
            ResourcesManager.rollbackTransactions(con);
            throw new RacunarskaOpremaException("Failed to delete search with ID " + idSearch, e);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
}
