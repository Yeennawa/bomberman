package com.bomberman.game;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameLoop extends JPanel implements ActionListener{
    private Timer timer;
    private GamePanel panel;

    public GameLoop(GamePanel panel) {
        this.panel = panel;
        timer = new Timer(16, this); // ~60 FPS
    }

    public void start() {
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        tick();
    }

    private void tick() {
        panel.updateGame();
        panel.repaint();
    }
}