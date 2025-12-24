package com.bomberman.game;
import javax.swing.*;
;

public class GameFrame extends JFrame {


    public GameFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Bomber man");
        this.setResizable(false);
        Menu menu = new Menu();
        this.add(menu);
        this.pack();

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}

