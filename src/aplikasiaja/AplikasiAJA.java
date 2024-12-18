package aplikasiaja;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AplikasiAJA {

    public static void main(String[] args) {
        // Create a frame
        JFrame frame = new JFrame("APLIKASI AKA");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 1));

        // Create UI components
        JLabel statusLabel = new JLabel("Status Database: -----", SwingConstants.CENTER);
        JTextField searchField = new JTextField();
        JButton connectButton = new JButton("Sambung ke Database");
        JButton iterativeSearchButton = new JButton("Pencarian Iteratif");
        JButton recursiveSearchButton = new JButton("Pencarian Rekursif");
        JLabel resultLabel = new JLabel("Nama Produk tidak ditemukan", SwingConstants.CENTER);
        JLabel timeLabel = new JLabel("Running Time tidak ada", SwingConstants.CENTER);

        // Add action listener to connect button
        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/AKASYSTEM", "root", "12345");
                    statusLabel.setText("Status Database : Terkoneksi");
                    System.out.println(con);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(AplikasiAJA.class.getName()).log(Level.SEVERE, null, ex);
                    statusLabel.setText("Status Database: Driver tidak ditemukan");
                } catch (SQLException ex) {
                    Logger.getLogger(AplikasiAJA.class.getName()).log(Level.SEVERE, null, ex);
                    statusLabel.setText("Status Database: Koneksi gagal");
                }
            }
        });

        // Add action listener to iterative search button
        iterativeSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String manufacturerName = searchField.getText().trim();
                if (!manufacturerName.isEmpty()) {
                    try {
                        // Start time tracking for iterative search
                        long startTime = System.nanoTime();

                        // Establish database connection
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection con = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/AKASYSTEM", "root", "12345"
                        );

                        // Perform iterative search
                        String result = IterativeSearch.search(con, manufacturerName);

                        // Stop time tracking
                        long endTime = System.nanoTime();
                        long duration = endTime - startTime;  // Duration in nanoseconds

                        if (result != null) {
                            resultLabel.setText("Nama Produk:  " + result);
                        } else {
                            resultLabel.setText("Produk tidak ditemukan");
                        }

                        // Display the running time
                        timeLabel.setText("Running time iterative: " + duration / 1000000.0 + " ms"); // Convert to milliseconds

                    } catch (Exception ex) {
                        Logger.getLogger(AplikasiAJA.class.getName()).log(Level.SEVERE, null, ex);
                        resultLabel.setText("Error saat pencarian");
                    }
                } else {
                    resultLabel.setText("Masukkan nama produk");
                }
            }
        });

        // Add action listener to recursive search button
        recursiveSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String manufacturerName = searchField.getText().trim();
                if (!manufacturerName.isEmpty()) {
                    try {
                        // Start time tracking for recursive search
                        long startTime = System.nanoTime();

                        // Establish database connection
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection con = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/AKASYSTEM", "root", "12345"
                        );

                        // Perform recursive search
                        String result = RecursiveSearch.search(con, manufacturerName);

                        // Stop time tracking
                        long endTime = System.nanoTime();
                        long duration = endTime - startTime;  // Duration in nanoseconds

                        if (result != null) {
                            resultLabel.setText("Nama Produk: " + result);
                        } else {
                            resultLabel.setText("Product tidak ditemukan");
                        }

                        // Display the running time
                        timeLabel.setText("Running time Rekursif: " + duration / 1000000.0 + " ms"); // Convert to milliseconds

                    } catch (Exception ex) {
                        Logger.getLogger(AplikasiAJA.class.getName()).log(Level.SEVERE, null, ex);
                        resultLabel.setText("Error saat pencarian");
                    }
                } else {
                    resultLabel.setText("Masukkan nama produk");
                }
            }
        });

        // Add components to panel
        panel.add(new JLabel("Masukkan nama produk yang akan dicari", SwingConstants.CENTER));
        panel.add(searchField);
        panel.add(connectButton);
        panel.add(iterativeSearchButton);
        panel.add(recursiveSearchButton);
        panel.add(resultLabel);  // Display result of search
        panel.add(timeLabel);    // Display search time
        panel.add(statusLabel);  // Connection status

        // Add panel to frame
        frame.add(panel);
        frame.setVisible(true);
    }
}
