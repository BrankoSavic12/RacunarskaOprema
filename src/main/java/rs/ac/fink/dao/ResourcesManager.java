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
            System.out.println("Loading MySQL JDBC driver...");
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("MySQL JDBC driver loaded successfully.");
        } catch (ClassNotFoundException ex) {
            System.err.println("Error: MySQL JDBC Driver not found.");
            ex.printStackTrace();
        }
    }

    // Kreira novu konekciju prema bazi podataka
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost/ComputerShop?user=root&password=&useSSL=false&serverTimezone=UTC";
        System.out.println("Attempting to connect to database with URL: " + url);
        return DriverManager.getConnection(url);
    }

    // Zatvara ResultSet i PreparedStatement
    public static void closeResources(ResultSet resultSet, PreparedStatement preparedStatement) {
        try {
            if (resultSet != null) {
                resultSet.close();
                System.out.println("ResultSet closed successfully.");
            }
        } catch (Exception e) {
            System.err.println("Error closing ResultSet:");
            e.printStackTrace();
        }
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
                System.out.println("PreparedStatement closed successfully.");
            }
        } catch (Exception e) {
            System.err.println("Error closing PreparedStatement:");
            e.printStackTrace();
        }
    }

    // Zatvara Connection
    public static void closeConnection(Connection con) {
        try {
            if (con != null) {
                con.close();
                System.out.println("Connection closed successfully.");
            }
        } catch (Exception e) {
            System.err.println("Error closing Connection:");
            e.printStackTrace();
        }
    }

    // Rollback transakcija
    public static void rollbackTransactions(Connection con) {
        try {
            if (con != null) {
                con.rollback();
                System.out.println("Transactions rolled back successfully.");
            }
        } catch (Exception e) {
            System.err.println("Error rolling back transactions:");
            e.printStackTrace();
        }
    }

    // Main metoda za testiranje konekcije
    public static void main(String[] args) {
        try (Connection con = getConnection()) {
            System.out.println("Connected to the database successfully!");
        } catch (SQLException e) {
            System.err.println("Database connection failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
