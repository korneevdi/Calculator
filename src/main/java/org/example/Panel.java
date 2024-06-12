package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Panel extends JPanel {

    // Array of buttons for numbers 0-9
    private JButton numbers[] = new JButton[10];
    private Font font = new Font("SanSerif", Font.BOLD, 20);
    private JTextField output = new JTextField(); // output field
    private JButton backspace = new JButton("X");
    private JButton equal = new JButton("=");
    private JButton plus = new JButton("+");
    private JButton minus = new JButton("-");
    private JButton multiply = new JButton("*");
    private JButton divide = new JButton("/");

    public Panel() {

        setLayout(null); // allows us to set elements wherever we want
        setFocusable(true); // allow us to enter symbols from the keyboard
        grabFocus();

        // backspace button
        backspace.setBounds(10, 250, 50, 50);
        backspace.setFont(font);
        add(backspace);

        // assignment button
        equal.setBounds(130, 250, 50, 50);
        equal.setFont(font);
        add(equal);

        // plus button
        plus.setBounds(190, 70, 50, 50);
        plus.setFont(font);
        add(plus);

        // minus button
        minus.setBounds(190, 130, 50, 50);
        minus.setFont(font);
        add(minus);

        // multiply button
        multiply.setBounds(190, 190, 50, 50);
        multiply.setFont(font);
        add(multiply);

        // divide button
        divide.setBounds(190, 250, 50, 50);
        divide.setFont(font);
        add(divide);

        // initialize zero button
        numbers[0] = new JButton("0");
        numbers[0].setBounds(70, 250, 50, 50);
        numbers[0].setFont(font);
        add(numbers[0]);

        // Initialize the numbers on the panel
        for(int x = 0; x < 3; x++) {
            for(int y = 0; y < 3; y++) {
                numbers[x * 3 + y + 1] = new JButton((x * 3 + y + 1) + "");
                numbers[x * 3 + y + 1].setBounds(x * 60 + 10, y * 60 + 70, 50, 50);
                numbers[x * 3 + y + 1].setFont(font);
                add(numbers[x * 3 + y + 1]);
            }
        }

        // Output field
        output.setBounds(10, 10, 230, 50);
        output.setEditable(false); // output text is immutable
        output.setFont(font);
        add(output);

        // Add functionality to the numbers
        ActionListener l = (ActionEvent e) -> {
            JButton b = (JButton) e.getSource();
            output.setText(output.getText() + b.getText());
        };

        for(JButton b : numbers) {
            b.addActionListener(l);
        }

        // Add possibility to enter numbers from the keyboard
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                char symbol = e.getKeyChar();
                if(!Character.isDigit(symbol)) {
                    return;
                } else {
                    output.setText(output.getText() + symbol);
                }
            }
        });
    }
}
