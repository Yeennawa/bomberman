package com.bomberman.entity;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SpriteSheet {
    private BufferedImage sheet;

    public SpriteSheet(String path) {
        try {
            // ใช้ getClass().getResourceAsStream() และใส่ / นำหน้า images
            var is = getClass().getResourceAsStream("/images/" + path);

            if (is == null) {
                throw new RuntimeException("หาไฟล์ไม่เจอที่: /images/" + path);
            }

            sheet = ImageIO.read(is);
        } catch (Exception e) {
            System.err.println("โหลด sprite ไม่ได้: " + path);
            e.printStackTrace();
        }
    }

    public BufferedImage grabSprite(int x, int y, int width, int height) {
        return sheet.getSubimage(x, y, width, height);
    }
}