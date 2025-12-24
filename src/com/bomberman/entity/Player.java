package com.bomberman.entity;

import java.awt.image.BufferedImage;

public class Player {
    public void Player(){

    }

    private void addplayer(){
        SpriteSheet ss = new SpriteSheet("bomb_party_v4.png");
        BufferedImage orangePlayer = ss.grabSprite(0, 128, 16, 16);
    }


}