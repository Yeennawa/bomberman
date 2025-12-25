package com.bomberman.entity;

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
    int tileSize = 32;
    int[][] tiles;
    int width = 28;
    int height = 28;
    ArrayList<Image> playerImages= new ArrayList<>();
    public Player(int x, int y, int speed, int boundary) {
        super(x, y, speed);
        this.width = boundary;
        keyH= new Keylistener();
        ss = new SpriteSheet("bomb_party_v4.png");
        playerImages.add(downImage  = ss.grabSprite(0, 271, 15, 15));
        playerImages.add(leftImage  = ss.grabSprite(15, 271, 15, 15));
        playerImages.add(rightImage = ss.grabSprite(30, 271, 15, 15));
        playerImages.add(upImage    = ss.grabSprite(45, 271, 15, 15));
    }
    public void draw(Graphics2D g2d) {
        if (currentImage != null) {
            g2d.drawImage(currentImage, x, y, 28, 28, null);
        }
    }
    public BufferedImage getSprite(int index) {
        return (BufferedImage) playerImages.get(index);
    }
    public Keylistener getKeyH() {
        return this.keyH;
    }

       public void checkCollision(int dx, int dy, GameMap map) {
            int nextX = x + dx;
            int nextY = y + dy;

            int left   = nextX;
            int right  = nextX + width - 1;
            int top    = nextY;
            int bottom = nextY + height - 1;

            int leftCol   = left   / map.tileSize;
            int rightCol  = right  / map.tileSize;
            int topRow    = top    / map.tileSize;
            int bottomRow = bottom / map.tileSize;

            if ( map.isBlocked(leftCol, topRow) ||
                    map.isBlocked(rightCol, topRow) ||
                    map.isBlocked(leftCol, bottomRow) ||
                    map.isBlocked(rightCol, bottomRow) ) {
                return;
            }

            x = nextX;
            y = nextY;

        }

}




