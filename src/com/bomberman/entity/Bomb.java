package com.bomberman.entity;
import com.bomberman.game.GamePanel;
import com.bomberman.map.GameMap;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Bomb extends Entity{
    private final SpriteSheet ss;
    private BufferedImage  boom;
    private int timer = 180;
    private boolean dead = false;
    public Bomb(int x,int y){
        super(x,y,0);
        this.x = x;
        this.y = y;
        ss = new SpriteSheet("bomb_party_v4.png");
        boom  = ss.grabSprite(80, 287, 16, 16);
    }

    public void draw(Graphics2D g2d) {
        if (boom != null) {
            g2d.drawImage(
                    boom,
                    x, y,
                    GamePanel.TILE_SIZE,
                    GamePanel.TILE_SIZE,
                    null
            );

        }
    }
    private void hit(int col, int row, ArrayList<Enemy> enemies,Player player) {

        if (player.getTileX() == col && player.getTileY() == row) {
            player.die();
        }

        for (Enemy e : enemies) {
            if (e.isDead()) continue;

            if (e.getTileX() == col && e.getTileY() == row) {
                e.die();
            }
        }
    }


    public void explode(ArrayList<Enemy> enemies, GameMap map,Player player) {
        int tx = getTileX();
        int ty = getTileY();

        hit(tx, ty, enemies,player);
        hit(tx + 1, ty, enemies,player);
        hit(tx - 1, ty, enemies,player);
        hit(tx, ty + 1, enemies,player);
        hit(tx, ty - 1, enemies,player);
    }

    public void update(ArrayList<Enemy> enemies, GameMap map, Player player) {
        timer--;

        if (timer == 0) {
            explode(enemies, map , player);
        }

        if (timer <= 0) {
            dead = true;
        }
    }

    public boolean isDead() {
        return dead;
    }


    public BufferedImage getCurrentImage() {
        return boom;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
}