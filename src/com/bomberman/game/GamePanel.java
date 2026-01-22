package com.bomberman.game;

import com.bomberman.entity.ApiClient;
import com.bomberman.entity.Bomb;
import com.bomberman.entity.Enemy;
import com.bomberman.entity.Player;
import com.bomberman.map.GameMap;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GamePanel extends JPanel implements ActionListener {
    private GameFrame frame;
    private int speed = 2;
    private int spriteIndex = 0;
    Player player;
    Keylistener keyH;
    GameMap map;
    ArrayList<Bomb> bombs = new ArrayList<>();
    ArrayList<Enemy> enemies ;
    public static final int TILE_SIZE = 48;
    public static final int COLS = 13;
    public static final int ROWS = 13;
    boolean bombPressed = false;

    public GamePanel(GameFrame frame) {
        this.frame = frame;
        map = new GameMap();
        keyH = new Keylistener();
        bombs = new ArrayList<>();
        player = new Player(
                1 * TILE_SIZE,
                1 * TILE_SIZE,
                speed,
                keyH
        );
        enemies = new ArrayList<>();
        spawnEnemies(3);

        this.keyH = player.getKeyH();
        setFocusable(true);
        addKeyListener(keyH);
        setPreferredSize(new Dimension(
                TILE_SIZE * COLS,
                TILE_SIZE * ROWS
        ));
        new Timer(16, this).start();
        setFocusable(true);
    }
    private void spawnEnemies(int count) {
        while (enemies.size() < count) {
            int col = (int)(Math.random() * GamePanel.COLS);
            int row = (int)(Math.random() * GamePanel.ROWS);

            if (map.getTile(row, col) != 0) continue;


            if (Math.abs(col - player.getTileX()) <= 2 &&
                    Math.abs(row - player.getTileY()) <= 2) continue;

            enemies.add(
                    new Enemy(col * GamePanel.TILE_SIZE,
                            row * GamePanel.TILE_SIZE,
                            2)
            );
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        map.draw(g2d);
        if (!player.isDead()) {
            player.draw(g2d);
        }
        for (Enemy e : enemies) {
            if (!e.isDead()) {
                e.draw(g2d);
            }
        }

        for (Bomb b : bombs) {
            b.draw(g2d);
        }
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 20));
        g2d.drawString("Score: " + player.getScore(), 10, 25);

    }

    public void updateGame() {
        int dx = 0;
        int dy = 0;
        int bombX = ((player.getX() + TILE_SIZE / 2) / TILE_SIZE) * TILE_SIZE;
        int bombY = ((player.getY() + TILE_SIZE / 2) / TILE_SIZE) * TILE_SIZE;
        if (keyH.boom && !bombPressed) {
            bombs.add(new Bomb(bombX, bombY, map));
            bombPressed = true;

        }

        if (!keyH.boom) {
            bombPressed = false;
        }
        for (int i = bombs.size() - 1; i >= 0; i--) {
            bombs.get(i).update(enemies, map, player);
            if (bombs.get(i).isDead()) {
                bombs.remove(i);
            }
        }

        for (Enemy enemy : enemies) {
            if (enemy.isDead() && !enemy.isScoreGiven()) {
                player.addScore(100);
                enemy.markScoreGiven();
            }
        }


        if (keyH.up) {
            dy = -speed;
            spriteIndex = 3;
        } else if (keyH.down) {
            dy = speed;
            spriteIndex = 0;
        } else if (keyH.left) {
            dx = -speed;
            spriteIndex = 1;
        } else if (keyH.right) {
            dx = speed;
            spriteIndex = 2;
        }

        if (dx != 0 || dy != 0) {
            player.checkCollision(dx, dy, map);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        updateGame();
        checkPlayerEnemyCollision();
        checkGameOver();
        if (!player.isDead()) {
            player.update(map);
        }
        for (Enemy enemy : enemies) {
            if (!enemy.isDead()) {
                enemy.update(map);
            }

        }
        repaint();
    }

    private void checkPlayerEnemyCollision() {
        int px = player.getTileX();
        int py = player.getTileY();

        for (Enemy e : enemies) {
            if (e.isDead()) continue;

            if (e.getTileX() == px && e.getTileY() == py) {
                player.die();
                return;
            }
        }
    }

    private boolean gameOver = false;

    private void checkGameOver() {
        if (player.isDead() && !gameOver) {
            gameOver = true;
            String leaderboard = ApiClient.getLeaderboard();
            frame.showMenu();
        }

    }
}
