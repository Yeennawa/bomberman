package com.bomberman.entity;

import com.bomberman.game.GamePanel;
import com.bomberman.game.Keylistener;
import com.bomberman.map.GameMap;
import com.bomberman.entity.ApiClient;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {
    private  Keylistener keyH;
    private final SpriteSheet ss;
    private BufferedImage upImage, downImage, leftImage, rightImage;
    private BufferedImage currentImage;
    int width  = GamePanel.TILE_SIZE;
    int height = GamePanel.TILE_SIZE;
    private String name = "";
    private int score = 0;

    public Player(int x, int y, int speed,Keylistener keyH,String name) {
        super(x, y, speed);
        this.keyH=keyH;
        this.name = name;


        ss = new SpriteSheet("bomb_party_v4.png");

        downImage  = ss.grabSprite(1, 272, 15, 15);
        rightImage  = ss.grabSprite(48, 272, 15, 15);
        leftImage = ss.grabSprite(32, 272, 15, 15);
        upImage    = ss.grabSprite(1, 272, 15, 15);

        currentImage = ss.grabSprite(17, 272, 15, 15);
    }

    public void addScore(int s) {
        score += s;
    }

    public int getScore() {
        return score;
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
    public BufferedImage getCurrentImage() {
        return currentImage;
    }

    public void update(GameMap map) {

        int dx = 0;
        int dy = 0;

        if (keyH.up) {
            dy = -speed;
            currentImage = upImage;
        }
        if (keyH.down) {
            dy = speed;
            currentImage = downImage;
        }
        if (keyH.left) {
            dx = -speed;
            currentImage = leftImage;
        }
        if (keyH.right) {
            dx = speed;
            currentImage = rightImage;
        }

        if (dx != 0 || dy != 0) {
            checkCollision(dx, dy, map);
        }

    }

    public Keylistener getKeyH() {
        return this.keyH;
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




