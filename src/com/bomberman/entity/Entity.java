package com.bomberman.entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

     protected int x, y;
     protected int speed;
     protected int width = 500, height = 500;

     public Entity(int x, int y, int speed) {
          this.x = x;
          this.y = y;
          this.speed = speed;
     }

     public void moveLeft(BufferedImage leftImage) {
          x -= speed;
          if (x < 0) x = 0;
     }

     public void moveRight(BufferedImage rightImage) {
          x += speed;
          if (x > width) x = width;
     }

     public void moveUp(BufferedImage upImage) {
          y -= speed;
          if (y < 0) y = 0;
     }

     public void moveDown(BufferedImage downImage) {
          y += speed;
          if (y > height) y = height;
     }

     public int getX() { return x; }
     public int getY() { return y; }

     public void draw(Graphics2D g2d, BufferedImage img) {
          if (img != null) {

               g2d.drawImage(img, x, y, 48, 48, null);
          }
     }
}