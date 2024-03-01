package main;

import utils.Utils;

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

    private JScrollPane outputScrollPane;
    private JScrollPane invScrollPane;
    private JScrollPane gearScrollPane;
    private JScrollPane pastInputScrollPane;
    private JTextArea outputArea;
    private JTextArea pastInputs;
    private JTextField inputField;
    private JTextArea invArea;
    private JTextArea gearArea;
    private JTabbedPane tabbedPane;

    public GUI() {
        JFrame frame = new JFrame("RPG");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(530,585);

        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);

        cont = frame.getContentPane();
        cont.setLayout(new GridBagLayout());

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);

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

        this.outputArea = new JTextArea(10,10);
        this.outputArea.setLineWrap(true);
        this.outputScrollPane = new JScrollPane(this.outputArea);
        this.outputScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.outputScrollPane.createVerticalScrollBar();
        this.outputScrollPane.setBorder(border);
        this.outputScrollPane.setPreferredSize(new Dimension(500,250));

        this.invArea = new JTextArea();
        this.invArea.setLineWrap(true);
        this.invScrollPane = new JScrollPane(this.invArea);
        this.invScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.invScrollPane.createVerticalScrollBar();
        this.invScrollPane.setPreferredSize(new Dimension(250,220));

        this.gearArea = new JTextArea(5,10);
        this.gearArea.setLineWrap(true);
        this.gearArea.setEditable(false);
        this.gearScrollPane = new JScrollPane(this.gearArea);
        this.gearScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.gearScrollPane.createVerticalScrollBar();
        this.gearScrollPane.setPreferredSize(new Dimension(250,220));

        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.addTab("Inventory", this.invScrollPane);
        this.tabbedPane.addTab("Gear", this.gearScrollPane);

        this.pastInputs = new JTextArea();
        this.pastInputScrollPane = new JScrollPane(this.pastInputs);
        this.pastInputScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.pastInputScrollPane.createVerticalScrollBar();
        this.pastInputScrollPane.setBorder(border);
        this.pastInputScrollPane.setPreferredSize(new Dimension(250,250));

        this.inputField = new JTextField();
        this.inputField.setBorder(border);
        this.inputField.setPreferredSize(new Dimension(250,20));


        this.tabbedPane.setBorder(border);
        this.tabbedPane.setSize(250,250);
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
        frame.add(this.outputScrollPane, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        this.pastInputs.setEditable(false);
        frame.add(this.pastInputScrollPane, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        frame.add(this.inputField, gbc);

        gbc.gridheight = 2;
        gbc.gridx = 1;
        gbc.gridy = 2;
        frame.add(this.tabbedPane, gbc);
        this.inputField.setSize(200,200);
        this.inputField.setFocusTraversalKeysEnabled(false);

        this.inputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
//                super.keyPressed(e);
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    handleInput();
                }

                if(e.getKeyCode() == KeyEvent.VK_TAB) {
                    String completed = Utils.autoCompleteInput(GUI.this.inputField.getText());
                    if(completed != null) {
                        GUI.this.inputField.setText(completed);
                    }
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

    public void setUserInput(String str) {
        this.inputField.setText(str);
    }

    public void handleInput() {
        String input = this.inputField.getText();
        this.pastInputs.append(this.inputField.getText() + "\n");
        this.clearUserInput();
        Utils.findInputHandler(input, Utils.questionInfo);

    }

    public void clearUserInput() {
        this.inputField.setText("");
    }

    public void setOutputArea(String str) {
        this.outputArea.setText(str);
    }

    public void appendToOutputArea(String str) {
        this.outputArea.append(str + "\n");
        outputArea.setCaretPosition(outputArea.getDocument().getLength());
    }

    public void clearOutputArea(){
        outputArea.setText("");
    }

    public void updateInventoryGUI() {
        this.invArea.setText(Game.player.inventory.toString());
    }

    public void updateGearGUI() {this.gearArea.setText(Game.player.gear.toString()); }

}
