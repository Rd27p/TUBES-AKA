/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.app_aka;

/**
 *
 * @author Raka Darma
 */
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI {
    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("Aplikasi AKA");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null); // Using null layout for simplicity

        // Create a label
        JLabel label = new JLabel("Nama: ");
        label.setBounds(20, 20, 100, 25);
        frame.add(label);

        // Create a text field
        JTextField textField = new JTextField();
        textField.setBounds(130, 20, 130, 25);
        frame.add(textField);

        // Create a button
        JButton button = new JButton("Say Hello");
        button.setBounds(90, 70, 120, 30);
        frame.add(button);

        // Create a label to display the message
        JLabel messageLabel = new JLabel("");
        messageLabel.setBounds(20, 110, 250, 25);
        frame.add(messageLabel);

        // Add action listener for button
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = textField.getText();
                messageLabel.setText("Hello, " + name + "!");
            }
        });
        // Make the frame visible
        frame.setVisible(true);
    }
}
