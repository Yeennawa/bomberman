package com.bomberman.game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    Image enemy;
    Image player;
    Image bomb;
    private int x=0,y=0,velX=0,velY=0;
    public GamePanel(){
        setFocusable(true);
        addKeyListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
    }
    public void updateGame() {
        x=x+velX;
        y=y+velY;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        updateGame();
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int c = e.getKeyCode();

        if(c==KeyEvent.VK_LEFT){
            velX=-1;
            velY=0;
        }

        if(c==KeyEvent.VK_RIGHT){
            velX=1;
            velY=0;
        }

        if(c==KeyEvent.VK_UP){
            velX=0;
            velY=1;
        }

        if(c==KeyEvent.VK_DOWN){
            velX=0;
            velY=-1;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}