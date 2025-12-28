package com.bomberman.game;

import com.bomberman.entity.Player;
import com.bomberman.map.GameMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        this.player=player;
        player = new Player(
                1 * TILE_SIZE,
                1 * TILE_SIZE,
                speed
        );
        this.keyH = player.getKeyH();
        setFocusable(true);
        addKeyListener(keyH);
        setPreferredSize(new Dimension(
                TILE_SIZE * COLS,
                TILE_SIZE * ROWS
        ));
        new Timer(16, this).start();
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        map.draw(g2d);
        g2d.drawImage(
                player.getCurrentImage(),
                player.getX(),
                player.getY(),
                TILE_SIZE,
                TILE_SIZE,
                null
        );
    }

    public void updateGame() {
        int dx = 0;
        int dy = 0;

        if (keyH.up) {
            dy = -speed;
            spriteIndex = 3;
        }
        else if (keyH.down) {
            dy = speed;
            spriteIndex = 0;
        }
        else if (keyH.left) {
            dx = -speed;
            spriteIndex = 1;
        }
        else if (keyH.right) {
            dx = speed;
            spriteIndex = 2;
        }

        if (dx != 0 || dy != 0) {
            player.checkCollision(dx, dy, map);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player.update(map);
        repaint();
    }

}