package com.hk.game;

import java.awt.*;

public class Bullet {
    public static final int DEFAULT_SPEED = Tank.DEFAULT_SPEED * 2;
    public static final int RADIUS = 4;
    private int x,y;
    private int speed = DEFAULT_SPEED;
    private int dir;
    private int atk;
    private Color color;

    public Bullet(int x, int y, int dir, int atk, Color color) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.atk = atk;
        this.color = color;
    }

    //绘制
    public void draw(Graphics g) {
        logic();
        g.setColor(color);
        g.fillOval(x - RADIUS, y - RADIUS, RADIUS << 1, RADIUS << 1);
    }

    private void logic() {
        move();
    }

    private void move() {
        switch (dir) {
            case Tank.DIR_UP:
                y -= speed;
                break;
            case Tank.DIR_DOWN:
                y += speed;
                break;
            case Tank.DIR_LEFT:
                x -= speed;
                break;
            case Tank.DIR_RIGHT:
                x += speed;
                break;
        }
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return x;
    }
    public int getDir() {
        return x;
    }
    public int getAtk() {
        return x;
    }
}
