package com.bomberman.map;

import com.bomberman.entity.SpriteSheet;
import com.bomberman.game.GamePanel;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class GameMap {
    private BufferedImage grass;
    private BufferedImage blox;
    private BufferedImage wall;
    public final int tileSize = GamePanel.TILE_SIZE;
    private int[][] mapData ;

    public void generateMap() {
        int row = 13, col = 13;
        mapData = new int[row][col];

        for (int y = 0; y < row; y++) {
            int i=0;
            for (int x = 0; x < col; x++) {
                int check=0;
                if (x == 0 || y == 0 || x == col - 1 || y == row - 1) {
                    mapData[y][x] = 1; // wall
                }

                else if (x % 2 == 0 && y % 2 == 0) {
                    mapData[y][x] = 1;
                }

                else if ((x == 1 && y == 1) ||
                        (x == 1 && y == 2) ||
                        (x == 2 && y == 1)) {
                    mapData[y][x] = 0;
                }
                else {
                    check = Math.random() < 0.7 ? 2 : 0;
                    if(check==2 && i <4){
                        mapData[y][x]=2;
                        i++;
                    }else {
                        mapData[y][x]=0;
                    }
                }
            }
        }
    }

    public GameMap() {
        SpriteSheet ss = new SpriteSheet("bomb_party_v4.png");
        grass = ss.grabSprite(16, 208, 16, 16);
        wall = ss.grabSprite(0, 0, 16, 16);
        blox = ss.grabSprite(144,208,16,16);
        generateMap();
    }
    public boolean isBlocked(int col, int row) {
        if (row < 0 || col < 0 ||
                row >= mapData.length ||
                col >= mapData[0].length) {
            return true;
        }
        return mapData[row][col] == 1 || mapData[row][col] == 2;
    }

    public void draw(Graphics2D g2d) {
        for (int row = 0; row < mapData.length; row++) {
            for (int col = 0; col < mapData[row].length; col++) {
                if(mapData[row][col] == 1){
                    g2d.drawImage(wall, col * tileSize, row * tileSize, tileSize, tileSize, null);
                }else if (mapData[row][col] == 2) {
                    g2d.drawImage(blox, col * tileSize, row * tileSize, tileSize, tileSize, null);
                }else {
                    g2d.drawImage(grass, col * tileSize, row * tileSize, tileSize, tileSize, null);
                }
            }
        }
    }
    public int getTile(int row, int col) {
        if (row < 0 || col < 0 ||
                row >= mapData.length ||
                col >= mapData[0].length) {
            return 1;
        }
        return mapData[row][col];
    }

    public void hitbox(int row, int col) {
        if (row < 0 || col < 0 ||
                row >= mapData.length ||
                col >= mapData[0].length) {
            return;
        }

        if (mapData[row][col] == 2) {
            mapData[row][col] = 0;
        }
    }
    public boolean isWall(int col, int row) {
        if (row < 0 || col < 0 ||
                row >= mapData.length ||
                col >= mapData[0].length) {
            return true;
        }
        return mapData[row][col] == 1;
    }
}