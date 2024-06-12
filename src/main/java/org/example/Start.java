package org.example;

import javax.swing.*;

public class Start {

    private JFrame window; // create new window

    // Constructor
    public Start() {
        window = new JFrame("Calculator"); // initialize window
        window.setSize(264, 345);
        window.add(new Panel()); // add panel
        window.setLocationRelativeTo(null); // location in the center
        window.setResizable(false); // size is immutable
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes by clicking on 'close'
        window.setVisible(true);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Start();
            }
        });
    }
}
