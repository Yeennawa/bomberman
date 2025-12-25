package com.bomberman.game;

import com.bomberman.entity.Player;
import com.bomberman.map.GameMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements ActionListener{
    private int x=0,y=0;
    private int speed=2;
    private int spriteIndex = 0;
    Player player;
    Keylistener keyH;
    GameMap map;
    public static final int TILE_SIZE = 48;
    public static final int COLS = 13;
    public static final int ROWS = 13;
    public GamePanel(){
        map = new GameMap();
        player = new Player(48,48,speed,500);
        this.player=player;
        this.keyH = player.getKeyH();
        setFocusable(true);
        addKeyListener(keyH);
        setPreferredSize(new Dimension(
                TILE_SIZE * COLS,
                TILE_SIZE * ROWS
        ));
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        map.draw(g2d);
        BufferedImage img = player.getSprite(spriteIndex);
        if (img != null) {
            player.draw(g2d, img);
        }
    }

    public void updateGame() {
        int dx = 0;
        int dy = 0;

        if (keyH.upPressed) {
            dy = -speed;
            spriteIndex = 3;
        }
        else if (keyH.downPressed) {
            dy = speed;
            spriteIndex = 0;
        }
        else if (keyH.leftPressed) {
            dx = -speed;
            spriteIndex = 1;
        }
        else if (keyH.rightPressed) {
            dx = speed;
            spriteIndex = 2;
        }

        if (dx != 0 || dy != 0) {
            player.checkCollision(dx, dy, map);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        updateGame();
        repaint();
    }
}