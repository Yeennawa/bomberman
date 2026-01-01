package com.bomberman.entity;

import com.bomberman.game.GamePanel;

public class Entity {

     protected int x, y;
     protected int speed;;

     public Entity(int x, int y, int speed) {
          this.x = x;
          this.y = y;
          this.speed = speed;
     }
     public int getX() { return x; }
     public int getY() { return y; }

     public int getTileX() {
          return (x + GamePanel.TILE_SIZE / 2) / GamePanel.TILE_SIZE;
     }

     public int getTileY() {
          return (y + GamePanel.TILE_SIZE / 2) / GamePanel.TILE_SIZE;
     }
}
