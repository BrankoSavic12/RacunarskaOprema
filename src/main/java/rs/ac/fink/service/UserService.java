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
import java.sql.Connection;
import java.sql.SQLException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserService {

    private static final UserService instance = new UserService();

    private UserService() {}

    public static UserService getInstance() {
        return instance;
    }

    public User getUserById(int idUser, Connection con) throws RacunarskaOpremaException {
        try {
            User user = UserDao.getInstance().find(idUser, con);
            if (user == null) {
                throw new RacunarskaOpremaException("User with ID " + idUser + " not found.");
            }
            return user;
        } catch (SQLException e) {
            throw new RacunarskaOpremaException("Failed to retrieve user with ID " + idUser, e);
        }
    }

    public List<User> getAllUsers(Connection con) throws RacunarskaOpremaException {
        try {
            return UserDao.getInstance().findAll(con);
        } catch (SQLException e) {
            throw new RacunarskaOpremaException("Failed to retrieve all users.", e);
        }
    }

    public int addUser(User user, Connection con) throws RacunarskaOpremaException {
        try {
            return UserDao.getInstance().insert(user, con);
        } catch (SQLException e) {
            throw new RacunarskaOpremaException("Failed to add user.", e);
        }
    }

    public void updateUser(User user, Connection con) throws RacunarskaOpremaException {
        try {
            UserDao.getInstance().update(user, con);
        } catch (SQLException e) {
            throw new RacunarskaOpremaException("Failed to update user with ID " + user.getIdUser(), e);
        }
    }

    public void deleteUser(int idUser, Connection con) throws RacunarskaOpremaException {
        try {
            UserDao.getInstance().delete(idUser, con);
        } catch (SQLException e) {
            throw new RacunarskaOpremaException("Failed to delete user with ID " + idUser, e);
        }
    }
}
