package com.bomberman.entity;

import com.bomberman.game.GamePanel;
import com.bomberman.map.GameMap;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Enemy extends Entity{
    private BufferedImage currentImage;
    private BufferedImage upImage, downImage, leftImage, rightImage;
    int width  = GamePanel.TILE_SIZE;
    private final SpriteSheet ss;
    int height = GamePanel.TILE_SIZE;
    private int direction = 0;
    private int moveCounter = 0;
    ArrayList<Enemy> enemies;
    public Enemy(int x,int y ,int speed){
        super(x, y, speed);
        ss = new SpriteSheet("bomb_party_v4.png");

        downImage  = ss.grabSprite(0, 271, 16, 16);
        leftImage  = ss.grabSprite(16, 271, 16, 16);
        rightImage = ss.grabSprite(32, 271, 16, 16);
        upImage    = ss.grabSprite(48, 271, 16, 16);

        currentImage = downImage;
    }
    public void draw(Graphics2D g2d) {
        if (currentImage != null) {
            g2d.drawImage(
                    currentImage,
                    x, y,
                    GamePanel.TILE_SIZE,
                    GamePanel.TILE_SIZE,
                    null
            );
        }
    }
    public void update(GameMap map) {
        moveCounter++;

        if (moveCounter > 60) {
            direction = (int)(Math.random() * 4);
            moveCounter = 0;
        }
        int dx = 0;
        int dy = 0;

        if (direction == 0) dy = -speed;
        if (direction == 1) dy = speed;
        if (direction == 2) dx = -speed;
        if (direction == 3) dx = speed;

        boolean moved = tryMove(dx, dy, map);
        
        if (!moved) {
            direction = (int)(Math.random() * 4);
        }
    }

    private boolean tryMove(int dx, int dy, GameMap map) {
        int oldX = x;
        int oldY = y;

        checkCollision(dx, dy, map);

        return oldX != x || oldY != y;
    }

    public BufferedImage getCurrentImage() {
        return currentImage;
    }
    public void checkCollision(int dx, int dy, GameMap map) {

        int offset = 2;

        if (dx != 0) {
            int nextX = x + dx;

            int left   = nextX + offset;
            int right  = nextX + width - offset ;
            int top    = y + offset;
            int bottom = y + height - offset ;

            int leftCol   = left  / map.tileSize;
            int rightCol  = right / map.tileSize;
            int topRow    = top   / map.tileSize;
            int bottomRow = bottom/ map.tileSize;

            if (!map.isBlocked(leftCol, topRow) &&
                    !map.isBlocked(rightCol, topRow) &&
                    !map.isBlocked(leftCol, bottomRow) &&
                    !map.isBlocked(rightCol, bottomRow)) {

                x = nextX;
            }
        }

        if (dy != 0) {
            int nextY = y + dy;

            int left   = x + offset;
            int right  = x + width - offset ;
            int top    = nextY + offset;
            int bottom = nextY + height - offset ;

            int leftCol   = left  / map.tileSize;
            int rightCol  = right / map.tileSize;
            int topRow    = top   / map.tileSize;
            int bottomRow = bottom/ map.tileSize;

            if (!map.isBlocked(leftCol, topRow) &&
                    !map.isBlocked(rightCol, topRow) &&
                    !map.isBlocked(leftCol, bottomRow) &&
                    !map.isBlocked(rightCol, bottomRow)) {

                y = nextY;
            }
        }
    }
        private boolean dead = false;

        public void die() {
            dead = true;
        }

        public boolean isDead() {
            return dead;
        }

}

