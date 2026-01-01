package com.bomberman.entity;
import com.bomberman.game.GamePanel;
import com.bomberman.map.GameMap;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Bomb extends Entity{
    private final SpriteSheet ss;
    private enum State { WAIT, EXPLODING }
    private State state = State.WAIT;
    private int timer= 180;
    private int explodeTime = 30;
    private BufferedImage  boom;
    private GameMap map;
    private BufferedImage fireCenter, fireUp, fireDown, fireLeft, fireRight;
    private boolean dead = false;
    private boolean explodedOnce = false;
    public Bomb(int x,int y, GameMap map){
        super(x,y,0);
        this.x = x;
        this.y = y;
        this.map = map;
        ss = new SpriteSheet("bomb_party_v4.png");
        boom  = ss.grabSprite(64, 288, 16, 16);
        fireCenter = ss.grabSprite(32, 288, 16, 16);
        fireUp     = ss.grabSprite(224, 211, 16, 16);
        fireDown   = ss.grabSprite(224, 240, 16, 16);
        fireLeft   = ss.grabSprite(0, 288, 16, 16);
        fireRight  = ss.grabSprite(48,288, 16, 16);

    }

    public void draw(Graphics2D g2d) {
        int s = GamePanel.TILE_SIZE;

        if (state == State.WAIT) {
            g2d.drawImage(boom, x, y, s, s, null);
        }
        else if (state == State.EXPLODING) {

            int tx = getTileX();
            int ty = getTileY();

            drawFire(g2d, fireCenter, tx, ty);
            if (ty - 1 >= 0 && !map.isWall(tx, ty - 1)) {
                drawFire(g2d, fireUp, tx, ty - 1);
            }
            if (ty + 1 >= 0 && !map.isWall(tx, ty + 1)) {
                drawFire(g2d, fireDown,   tx, ty + 1);
            }
            if (tx - 1 >= 0 && !map.isWall(tx - 1, ty )) {
                drawFire(g2d, fireLeft,   tx - 1, ty);
            }
            if (tx + 1 >= 0 && !map.isWall(tx + 1 , ty)) {
                drawFire(g2d, fireRight,  tx + 1, ty);
            }
        }
    }
    private void hit(int col, int row,
                     ArrayList<Enemy> enemies,
                     Player player,
                     GameMap map) {

        if (player.getTileX() == col && player.getTileY() == row) {
            player.die();
        }

        map.hitbox(row, col);

        for (Enemy e : enemies) {
            if (!e.isDead()
                    && e.getTileX() == col
                    && e.getTileY() == row) {
                e.die();
            }
        }
    }

    public void explode(ArrayList<Enemy> enemies, GameMap map, Player player) {
        int tx = getTileX();
        int ty = getTileY();

        hit(tx, ty, enemies, player, map);

        if (!map.isWall(tx, ty - 1))
            hit(tx, ty - 1, enemies, player, map);

        if (!map.isWall(tx, ty + 1))
            hit(tx, ty + 1, enemies, player, map);

        if (!map.isWall(tx - 1, ty))
            hit(tx - 1, ty, enemies, player, map);

        if (!map.isWall(tx + 1, ty))
            hit(tx + 1, ty, enemies, player, map);
    }

    private void drawFire(Graphics2D g2d, BufferedImage img, int col, int row) {
        int s = GamePanel.TILE_SIZE;
        g2d.drawImage(img, col * s, row * s, s, s, null);
    }

    public void update(ArrayList<Enemy> enemies, GameMap map, Player player) {
        if (state == State.WAIT) {
            timer--;
            if (timer <= 0) {
                explode(enemies, map, player);
                explodedOnce = true;
                state = State.EXPLODING;
            }
        }
        else if (state == State.EXPLODING) {
            explodeTime--;
            if (explodeTime <= 0) {
                dead = true;
            }
        }

    }

    public boolean isDead() {
        return dead;
    }


    public BufferedImage getCurrentImage() {
        return boom;
    }
}