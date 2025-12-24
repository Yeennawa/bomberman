package com.bomberman.entity; //

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SpriteSheet {
    private BufferedImage sheet;

    public SpriteSheet(String path) {
        try {
            sheet = ImageIO.read(new File("images/" + path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage grabSprite(int x, int y, int width, int height) {
        return sheet.getSubimage(x, y, width, height);
    }
}