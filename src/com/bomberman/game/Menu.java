package com.bomberman.game;
import javax.swing.*;
import java.awt.*;

public class Menu extends JPanel {

    public Menu() {
        this.add(background());
    }

    private JButton startButton() {
        JButton startButton = new JButton("START GAME");
        startButton.addActionListener(e -> {
            System.out.println("เริ่มเกม!");
        });
        startButton.setPreferredSize(new Dimension(200, 50));

        return startButton;
    }

    private JButton exitButton() {
        JButton exitButton = new JButton("EXIT GAME");
        exitButton.addActionListener(e -> {
            System.exit(0);
        });
        exitButton.setPreferredSize(new Dimension(200, 50));

        return exitButton;
    }

    private JLabel background(){
        ImageIcon background = new ImageIcon("src/images/backfree.jpg");
        JLabel lebel = new JLabel(background);
        lebel.setLayout(null);
        JButton start = startButton();
        start.setBounds(150, 350, 175, 40);
        lebel.add(start);
        JButton exit = exitButton();
        exit.setBounds(150,400,175,40);
        lebel.add(exit);
        return lebel;
    }
}