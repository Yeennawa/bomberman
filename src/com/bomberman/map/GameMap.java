package com.bomberman.map;

import com.bomberman.entity.SpriteSheet;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class GameMap {
    private BufferedImage grass;
    private BufferedImage blox;
    public final int tileSize = 48;

    public int[][] mapData = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };
    public GameMap() {
        SpriteSheet ss = new SpriteSheet("bomb_party_v4.png");
        grass = ss.grabSprite(64, 48, 15, 15);
        blox = ss.grabSprite(48, 0, 15, 15);
    }
    public boolean isBlocked(int col, int row) {

        if (row < 0 || col < 0 ||
                row >= mapData.length ||
                col >= mapData[0].length) {
            return true;
        }

        return mapData[row][col] == 1;
    }
    public void draw(Graphics2D g2d) {
        for (int row = 0; row < mapData.length; row++) {
            for (int col = 0; col < mapData[row].length; col++) {
                BufferedImage img = mapData[row][col] == 1 ? blox : grass;
                g2d.drawImage(img, col * tileSize, row * tileSize, tileSize, tileSize, null);
            }
        }
}}