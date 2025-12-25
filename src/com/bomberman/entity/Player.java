package com.bomberman.entity;

public class Player extends Entity {
    public Player(int x, int y, int speed, int boundary) {
        super(x, y, speed);
        this.width = boundary;
    }
}



