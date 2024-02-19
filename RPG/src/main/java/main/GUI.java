package main;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class GUI {

    private Container cont;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem save, load, exit;
    private JTextArea outputArea;
    private JTextArea pastInputs;
    private JTextField inputField;
    private JTable inventory;

    public GUI() {
        JFrame frame = new JFrame("RPG");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(520,565);

        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);

        cont = frame.getContentPane();
        cont.setLayout(new GridBagLayout());

        this.outputArea = new JTextArea();
        this.pastInputs = new JTextArea();
        this.inputField = new JTextField();
        this.inventory = new JTable();

        this.save = new JMenuItem("Save");
        this.load = new JMenuItem("Load");
        this.exit = new JMenuItem("Exit");

        this.menu = new JMenu("Game");
        this.menu.add(this.save);
        this.menu.add(this.load);
        this.menu.add(this.exit);

        this.menuBar = new JMenuBar();
        this.menuBar.setSize(500,30);
        this.menuBar.add(this.menu);

        this.menuBar.setBorder(border);

        this.outputArea.setBorder(border);
        this.outputArea.setPreferredSize(new Dimension(500,250));

        this.pastInputs.setBorder(border);
        this.pastInputs.setPreferredSize(new Dimension(250,230));

        this.inputField.setBorder(border);
        this.inputField.setPreferredSize(new Dimension(250,20));

        this.inventory.setBorder(border);
        this.inventory.setPreferredSize(new Dimension(250,250));

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.ipadx = 2;
        gbc.ipady = 0;

        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        frame.add(this.menuBar, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        this.outputArea.setEditable(false);
        frame.add(this.outputArea, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        this.pastInputs.setEditable(false);
        frame.add(this.pastInputs, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        frame.add(this.inputField, gbc);

        gbc.gridheight = 2;
        gbc.gridx = 1;
        gbc.gridy = 2;
        frame.add(this.inventory, gbc);
        this.inputField.setSize(200,200);
//        frame.pack();



        this.inputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    handleInput();
                }
            }
        });

        this.save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Commands.saveGame();
            }
        });
        this.load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Commands.loadGame();
            }
        });
        this.exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
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
        if(Utils.questionHandler == null) {
            Utils.questionHandler = Game.commands;
        }
        String input = this.inputField.getText();
        this.pastInputs.append(this.inputField.getText() + "\n");
        this.clearUserInput();
        System.out.println(Utils.questionHandler == null);
        Utils.questionHandler.handleAnswer(input, Utils.questionInfo);

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

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
