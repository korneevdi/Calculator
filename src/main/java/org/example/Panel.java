package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Panel extends JPanel {

    private JButton numbers[] = new JButton[10]; // Array of buttons for numbers 0-9
    private Font font = new Font("SanSerif", Font.BOLD, 20);
    private JTextField output = new JTextField(); // output field
    private JButton backspace = new JButton("X");
    private JButton equal = new JButton("=");
    private JButton plus = new JButton("+");
    private JButton minus = new JButton("-");
    private JButton multiply = new JButton("*");
    private JButton divide = new JButton("/");

    // Variables to store the current calculation state
    private double result = 0;
    private String operator = "";
    private boolean isOperatorPressed = false;

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
            if (isOperatorPressed) {
                output.setText("");
                isOperatorPressed = false;
            }
            output.setText(output.getText() + b.getText());
        };

        for(JButton b : numbers) {
            b.addActionListener(l);
        }

        // Add functionality to the operators
        plus.addActionListener(e -> handleOperator("+"));
        minus.addActionListener(e -> handleOperator("-"));
        multiply.addActionListener(e -> handleOperator("*"));
        divide.addActionListener(e -> handleOperator("/"));

        // Add functionality to the equal button
        equal.addActionListener(e -> calculate());

        // Add functionality to the backspace button
        backspace.addActionListener(e -> {
            String text = output.getText();
            if (text.length() > 0) {
                output.setText(text.substring(0, text.length() - 1));
            }
        });

        // Add possibility to enter numbers and operators from the keyboard
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                char symbol = e.getKeyChar();
                if (Character.isDigit(symbol)) {
                    if (isOperatorPressed) {
                        isOperatorPressed = false;
                    }
                    output.setText(output.getText() + symbol);
                } else if (symbol == '+') {
                    handleOperator("+");
                } else if (symbol == '-') {
                    handleOperator("-");
                } else if (symbol == '*') {
                    handleOperator("*");
                } else if (symbol == '/') {
                    handleOperator("/");
                } else if (symbol == '=') {
                    calculate();
                } else if (symbol == KeyEvent.VK_BACK_SPACE) {
                    String text = output.getText();
                    if (text.length() > 0) {
                        output.setText(text.substring(0, text.length() - 1));
                    }
                }
            }
        });
    }

    private void handleOperator(String op) {
        if (!output.getText().isEmpty() && !isOperatorPressed) {
            result = Double.parseDouble(output.getText());
            output.setText(output.getText() + op);
            operator = op;
            isOperatorPressed = true;
        }
    }

    private void calculate() {
        if (!operator.isEmpty() && !output.getText().isEmpty()) {
            String text = output.getText();
            String[] parts = text.split("\\" + operator);
            if (parts.length == 2) {
                double secondOperand = Double.parseDouble(parts[1]);
                switch (operator) {
                    case "+":
                        result += secondOperand;
                        break;
                    case "-":
                        result -= secondOperand;
                        break;
                    case "*":
                        result *= secondOperand;
                        break;
                    case "/":
                        result /= secondOperand;
                        break;
                }
                output.setText(String.valueOf(result));
                operator = "";
                isOperatorPressed = false;
            }
        }
    }
}
