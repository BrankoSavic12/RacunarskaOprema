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

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SearchService {
    private static final SearchService instance = new SearchService();

    private SearchService() {}

    public static SearchService getInstance() {
        return instance;
    }

    public Search getSearchById(int idSearch, Connection con) throws RacunarskaOpremaException {
        try {
            Search search = SearchDao.getInstance().find(idSearch, con);
            if (search == null) {
                throw new RacunarskaOpremaException("Search with ID " + idSearch + " not found.");
            }
            return search;
        } catch (SQLException e) {
            throw new RacunarskaOpremaException("Failed to retrieve search with ID " + idSearch, e);
        }
    }

    public List<Search> getAllSearches(Connection con) throws RacunarskaOpremaException {
        try {
            return SearchDao.getInstance().findAll(con);
        } catch (SQLException e) {
            throw new RacunarskaOpremaException("Failed to retrieve all searches.", e);
        }
    }

    public int addSearch(Search search, Connection con) throws RacunarskaOpremaException {
        try {
            return SearchDao.getInstance().insert(search, con);
        } catch (SQLException e) {
            throw new RacunarskaOpremaException("Failed to add search.", e);
        }
    }

    public void updateSearch(Search search, Connection con) throws RacunarskaOpremaException {
        try {
            SearchDao.getInstance().update(search, con);
        } catch (SQLException e) {
            throw new RacunarskaOpremaException("Failed to update search with ID " + search.getIdSearch(), e);
        }
    }

    public void deleteSearch(int idSearch, Connection con) throws RacunarskaOpremaException {
        try {
            SearchDao.getInstance().delete(idSearch, con);
        } catch (SQLException e) {
            throw new RacunarskaOpremaException("Failed to delete search with ID " + idSearch, e);
        }
    }
}
