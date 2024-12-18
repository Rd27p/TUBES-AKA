/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplikasiaja;

/**
 *
 * @author Raka Darma
 */
import java.sql.*;

public class RecursiveSearch {
    public static String search(Connection con, String productName) {
        String query = "SELECT productName FROM `gpu data`.gpu_spec_final";
        try (PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            return recursiveSearch(rs, productName);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null; // No match found
    }

    private static String recursiveSearch(ResultSet rs, String productName) {
        try {
            if (rs.next()) {
                String currentProductName = rs.getString("productName");

                // Compare current product name to the provided product name (case-insensitive)
                if (currentProductName.toLowerCase().contains(productName.toLowerCase())) {
                    return currentProductName; // Return the product name if it matches
                } else {
                    // Recursively search the next row
                    return recursiveSearch(rs, productName);
                }
            } else {
                return null; // No match found if the result set is exhausted
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null; // Handle any exceptions or errors
    }
}

