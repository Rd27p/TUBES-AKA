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

public class IterativeSearch {
    public static String search(Connection con, String productName) {
        String query = "SELECT productName FROM `gpu data`.gpu_spec_final";
        try (PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String currentProductName = rs.getString("productName"); // Correct column name
                if (currentProductName.toLowerCase().contains(productName.toLowerCase())) {
                    return currentProductName; // Return the product name if it matches
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null; // No match found
    }
}

