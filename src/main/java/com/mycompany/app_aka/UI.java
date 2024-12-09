/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.app_aka;

/**
 *
 * @author Raka Darma
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.util.List;

public class UI {

    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;
    private List<GPU> gpuList;

    public UI() {
        initialize();
    }

    private void initialize() {
        // Set up the main frame
        frame = new JFrame("GPU Sorting");
        frame.setSize(800, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Table for displaying GPU data
        tableModel = new DefaultTableModel(new String[]{"Name", "Memory Size", "TMU"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Buttons for actions
        JButton loadButton = new JButton("Load Data");
        JButton sortButton = new JButton("Sort by Memory Size");

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(loadButton);
        buttonPanel.add(sortButton);

        // Add components to the frame
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Button actions
        loadButton.addActionListener(e -> loadData());
        sortButton.addActionListener(e -> sortData());

        frame.setVisible(true);
    }

    private void loadData() {
        Connection connection = DatabaseUtils.connectDatabase();
        if (connection == null) {
            JOptionPane.showMessageDialog(frame, "Failed to connect to the database!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        gpuList = DatabaseUtils.fetchGPUData(connection);
        updateTable();

        try {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sortData() {
        if (gpuList == null || gpuList.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No data to sort!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        SortingUtils.bubbleSort(gpuList);
        updateTable();
    }

    private void updateTable() {
        tableModel.setRowCount(0); // Clear existing rows
        if (gpuList != null) {
            for (GPU gpu : gpuList) {
                tableModel.addRow(new Object[]{gpu.name, gpu.memSize, gpu.tmu});
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(UI::new);
    }
}
