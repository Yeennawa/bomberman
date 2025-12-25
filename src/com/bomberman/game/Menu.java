package com.bomberman.game;
import javax.swing.*;
import java.awt.*;

public class Menu extends JPanel {
    GamePanel panel;
    GameFrame frame;
    public Menu(GameFrame frame) {
        this.frame = frame;
        this.setLayout(null);
        this.setPreferredSize(new Dimension(500, 500));

        JButton startBtn = startButton();
        startBtn.setBounds(150, 350, 200, 40);

        JButton exitBtn = exitButton();
        exitBtn.setBounds(150, 400, 200, 40);

        this.add(startBtn);
        this.add(exitBtn);

        JLabel bg = background();
        bg.setBounds(0, 0, 500, 500);
        this.add(bg);

    }

    private JButton startButton() {
        JButton startButton = new JButton("START GAME");
        startButton.addActionListener(e -> {
            frame.startGame();
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