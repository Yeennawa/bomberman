package com.bomberman.entity;

public class Entity {

     protected int x, y;
     protected int speed;
     protected int width = 500, height = 500;

     public Entity(int x, int y, int speed) {
          this.x = x;
          this.y = y;
          this.speed = speed;
     }

     public void moveLeft() {
          x -= speed;
          if (x < 0) x = 0;
     }

     public void moveRight() {
          x += speed;
          if (x > width) x = width;
     }

     public void moveUp() {
          y -= speed;
          if (y < 0) y = 0;
     }

     public void moveDown() {
          y += speed;
          if (y > height) y = height;
     }

     public int getX() { return x; }
     public int getY() { return y; }
}