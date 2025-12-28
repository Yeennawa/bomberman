package com.bomberman.entity;

import com.bomberman.game.GamePanel;
import com.bomberman.game.Keylistener;
import com.bomberman.map.GameMap;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends Entity {
    private final Keylistener keyH;
    private final SpriteSheet ss;
    private BufferedImage upImage, downImage, leftImage, rightImage;
    private BufferedImage currentImage;
    private int dx,dy;
    int[][] tiles;
    int width  = GamePanel.TILE_SIZE;
    int height = GamePanel.TILE_SIZE;
    ArrayList<Image> playerImages= new ArrayList<>();
    public Player(int x, int y, int speed) {
        super(x, y, speed);
        keyH = new Keylistener();
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
            int right  = nextX + width - offset - 1;
            int top    = y + offset;
            int bottom = y + height - offset - 1;

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
            int right  = x + width - offset - 1;
            int top    = nextY + offset;
            int bottom = nextY + height - offset - 1;

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

    private int snapToGrid(int value, int tileSize) {
        return (value / tileSize) * tileSize + (tileSize - height) / 2;
    }
}




