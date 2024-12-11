/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.fink.dao;

/**
 *
 * @author MyPC
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResourcesManager {

    static {
        try {
            // Učitaj JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Kreira novu konekciju prema bazi podataka
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost/ComputerShop?user=root&password=");
    }

    // Zatvara ResultSet i PreparedStatement
    public static void closeResources(ResultSet resultSet, PreparedStatement preparedStatement) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Zatvara Connection
    public static void closeConnection(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Rollback transakcija
    public static void rollbackTransactions(Connection con) {
        try {
            if (con != null) {
                con.rollback();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
