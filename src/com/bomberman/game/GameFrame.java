package com.bomberman.game;
import javax.swing.*;
;

public class GameFrame extends JFrame {


    public GameFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Bomber man");
        this.setResizable(false);
        this.add(new Menu(this));
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void startGame(){
        this.getContentPane().removeAll();
        GamePanel panel = new GamePanel();
        this.add(panel);
        this.pack();
        this.revalidate();
        panel.requestFocus();
        GameLoop loop = new GameLoop(panel);
        loop.start();
    }
}

