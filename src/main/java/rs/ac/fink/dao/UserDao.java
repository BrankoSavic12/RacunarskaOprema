/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.fink.dao;

/**
 *
 * @author MyPC
 */

import rs.ac.fink.data.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    private static final UserDao instance = new UserDao();

    private UserDao() {}

    public static UserDao getInstance() {
        return instance;
    }

    public User find(int idUser, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;
        try {
            ps = con.prepareStatement("SELECT * FROM user WHERE id_user=?");
            ps.setInt(1, idUser);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = new User(
                        rs.getInt("id_user"),
                        rs.getString("full_name"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("birth_date"),
                        rs.getLong("account_balance"),
                        rs.getLong("spent_amount")
                );
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return user;
    }

    public List<User> findAll(Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<User> users = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM user");
            rs = ps.executeQuery();
            while (rs.next()) {
                users.add(new User(
                        rs.getInt("id_user"),
                        rs.getString("full_name"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("birth_date"),
                        rs.getLong("account_balance"),
                        rs.getLong("spent_amount")
                ));
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return users;
    }

      public int insert(User user, Connection con) throws SQLException {
    PreparedStatement ps = null;
    try {
        ps = con.prepareStatement(
                "INSERT INTO user (id_user, full_name, username, email, birth_date, account_balance, spent_amount) VALUES (?, ?, ?, ?, ?, ?, ?)"
        );
        ps.setInt(1, user.getIdUser());        
        ps.setString(2, user.getFullName());
        ps.setString(3, user.getUsername());
        ps.setString(4, user.getEmail());
        ps.setString(5, user.getBirthDate()); 
        ps.setLong(6, user.getAccountBalance());
        ps.setLong(7, user.getSpentAmount());

        ps.executeUpdate();

        return user.getIdUser(); 
    } finally {
        ResourcesManager.closeResources(null, ps);
    }
}

    public void update(User user, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(
                    "UPDATE user SET full_name=?, username=?, email=?, birth_date=?, account_balance=?, spent_amount=? WHERE id_user=?"
            );
            ps.setString(1, user.getFullName());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getBirthDate());
            ps.setLong(5, user.getAccountBalance());
            ps.setLong(6, user.getSpentAmount());
            ps.setInt(7, user.getIdUser());
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }

    public void delete(int idUser, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("DELETE FROM user WHERE id_user=?");
            ps.setInt(1, idUser);
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }
}
