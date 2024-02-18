package main;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;

public class GUI {

    private Container cont;
    private JTextArea outputArea;
    private JTextArea pastInputs;
    private JTextField inputField;
    private JTable inventory;

    public GUI() {
        JFrame frame = new JFrame("RPG");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,530);

        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);

        cont = frame.getContentPane();
        cont.setLayout(null);

        this.outputArea.setSize(496, 247);
        this.outputArea.setLocation(1, 1);
        this.outputArea.setBorder(border);
        cont.add(this.outputArea);

        this.pastInputs.setSize(246, 228);
        this.pastInputs.setLocation(1, 251);
        this.pastInputs.setBorder(border);
        cont.add(this.pastInputs);

        this.inputField.setSize(246, 18);
        this.inputField.setLocation(1, 482);
        cont.add(this.inputField);
        this.inputField.setBorder(border);

        this.inventory.setSize(246, 248);
        this.inventory.setLocation(251, 251);
        cont.add(this.inventory);
        this.inventory.setBorder(border);


        inputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    handleInput();
                }
            }
        });

        frame.setVisible(true);
    }

    public String getUserInput() {
        return this.inputField.getText();
    }

    public void setUserInput(String str) {
        this.inputField.setText(str);
    }

    public void handleInput() {
        String input = inputField.getText();
        this.pastInputs.append(this.inputField.getText() + "\n");
        this.clearUserInput();
        Utils.questionHandler.handleAnswer(input);
    }

    public void clearUserInput() {
        this.inputField.setText("");
    }

    public void setOutputArea(String str) {
        this.outputArea.setText(str);
    }

    public void appendToOutputArea(String str) {
        this.outputArea.append("\n" + str);
    }

    public void clearOutputArea(){
        outputArea.setText("");
    }

}
