/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.fink.service;

/**
 *
 * @author MyPC
 */

import rs.ac.fink.dao.UserDao;
import rs.ac.fink.data.User;
import rs.ac.fink.exception.RacunarskaOpremaException;
import rs.ac.fink.dao.ResourcesManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserService {

    private static final UserService instance = new UserService();

    private UserService() {}

    public static UserService getInstance() {
        return instance;
    }

    public User getUserById(int idUser) throws RacunarskaOpremaException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            return UserDao.getInstance().find(idUser, con);
        } catch (SQLException e) {
            throw new RacunarskaOpremaException("Failed to retrieve user with ID " + idUser, e);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public List<User> getAllUsers() throws RacunarskaOpremaException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            return UserDao.getInstance().findAll(con);
        } catch (SQLException e) {
            throw new RacunarskaOpremaException("Failed to retrieve all users.", e);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public int addUser(User user) throws RacunarskaOpremaException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            int userId = UserDao.getInstance().insert(user, con);

            con.commit();
            return userId;
        } catch (SQLException e) {
            ResourcesManager.rollbackTransactions(con);
            throw new RacunarskaOpremaException("Failed to add user.", e);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public void updateUser(User user) throws RacunarskaOpremaException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            UserDao.getInstance().update(user, con);

            con.commit();
        } catch (SQLException e) {
            ResourcesManager.rollbackTransactions(con);
            throw new RacunarskaOpremaException("Failed to update user with ID " + user.getIdUser(), e);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public void deleteUser(int idUser) throws RacunarskaOpremaException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            UserDao.getInstance().delete(idUser, con);

            con.commit();
        } catch (SQLException e) {
            ResourcesManager.rollbackTransactions(con);
            throw new RacunarskaOpremaException("Failed to delete user with ID " + idUser, e);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    void updateUser(User user, Connection con) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
