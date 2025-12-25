package com.bomberman.game;

import com.bomberman.entity.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel implements ActionListener{
    private int x=0,y=0,velX=0,velY=0;
    private int speed=5;
    Player player;
    Keylistener keyH;
    public GamePanel(){
        player = new Player(x,y,speed,500);
        setFocusable(true);
        addKeyListener(keyH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
    }

    public void updateGame() {
        if(keyH.leftPressed)player.moveLeft();
        if(keyH.rightPressed)player.moveRight();
        if(keyH.upPressed)player.moveUp();
        if(keyH.downPressed)player.moveDown();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        updateGame();
        repaint();
    }
}