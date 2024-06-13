package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Panel extends JPanel {

    private JButton[] numbers = new JButton[10];
    private Font font = new Font("SanSerif", Font.BOLD, 20);
    private JTextField output = new JTextField();
    private JButton backspace = new JButton("X");
    private JButton equal = new JButton("=");
    private JButton plus = new JButton("+");
    private JButton minus = new JButton("-");
    private JButton multiply = new JButton("*");
    private JButton divide = new JButton("/");

    public Panel() {
        setLayout(null);
        setFocusable(true);
        grabFocus();

        backspace.setBounds(10, 250, 50, 50);
        backspace.setFont(font);
        add(backspace);

        equal.setBounds(130, 250, 50, 50);
        equal.setFont(font);
        add(equal);

        plus.setBounds(190, 70, 50, 50);
        plus.setFont(font);
        add(plus);

        minus.setBounds(190, 130, 50, 50);
        minus.setFont(font);
        add(minus);

        multiply.setBounds(190, 190, 50, 50);
        multiply.setFont(font);
        add(multiply);

        divide.setBounds(190, 250, 50, 50);
        divide.setFont(font);
        add(divide);

        numbers[0] = new JButton("0");
        numbers[0].setBounds(70, 250, 50, 50);
        numbers[0].setFont(font);
        add(numbers[0]);

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                numbers[x * 3 + y + 1] = new JButton((x * 3 + y + 1) + "");
                numbers[x * 3 + y + 1].setBounds(x * 60 + 10, y * 60 + 70, 50, 50);
                numbers[x * 3 + y + 1].setFont(font);
                add(numbers[x * 3 + y + 1]);
            }
        }

        output.setBounds(10, 10, 230, 50);
        output.setEditable(false);
        output.setFont(font);
        add(output);

        ActionListener l = (ActionEvent e) -> {
            JButton b = (JButton) e.getSource();
            output.setText(output.getText() + b.getText());
        };

        for (JButton b : numbers) {
            b.addActionListener(l);
        }

        plus.addActionListener(e -> handleOperator("+"));
        minus.addActionListener(e -> handleOperator("-"));
        multiply.addActionListener(e -> handleOperator("*"));
        divide.addActionListener(e -> handleOperator("/"));

        equal.addActionListener(e -> calculate());

        backspace.addActionListener(e -> {
            String text = output.getText();
            if (text.length() > 0) {
                output.setText(text.substring(0, text.length() - 1));
            }
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                char symbol = e.getKeyChar();
                if (Character.isDigit(symbol)) {
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
        if (!output.getText().isEmpty() && !output.getText().endsWith(op)) {
            output.setText(output.getText() + op);
        }
    }

    private void calculate() {
        try {
            String text = output.getText();
            String regex = "(?=[-+*/])|(?<=[-+*/])";
            String[] parts = text.split(regex);

            double result = Double.parseDouble(parts[0]);
            for (int i = 1; i < parts.length; i += 2) {
                String operator = parts[i];
                double operand = Double.parseDouble(parts[i + 1]);

                switch (operator) {
                    case "+":
                        result += operand;
                        break;
                    case "-":
                        result -= operand;
                        break;
                    case "*":
                        result *= operand;
                        break;
                    case "/":
                        result /= operand;
                        break;
                }
            }

            output.setText(String.valueOf(result));
        } catch (Exception e) {
            output.setText("Error");
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(260, 350);
        frame.add(new Panel());
        frame.setVisible(true);
    }
}
