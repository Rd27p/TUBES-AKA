/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.app_aka;

/**
 *
 * @author Raka Darma
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseUtils {

    public static Connection connectDatabase() {
        String url = "mysql://127.0.0.1:3306/AKASYSTEM";
        String user = "root";
        String password = "12345";
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Fetch GPU data from the database
    public static List<GPU> fetchGPUData(Connection connection) {
        List<GPU> gpuList = new ArrayList<>();
        String query = "SELECT productName, memSize, tmu FROM gpu_data";  // Adjust table name if needed

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String name = rs.getString("productName");
                int memSize = rs.getInt("memSize");
                int tmu = rs.getInt("tmu");
                gpuList.add(new GPU(name, memSize, tmu));  // Fixed the variable name
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gpuList;
    }
}

