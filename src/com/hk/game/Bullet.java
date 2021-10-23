package com.hk.game;

import com.hk.util.Constant;

import java.awt.*;

public class Bullet {
    public static final int DEFAULT_SPEED = Tank.DEFAULT_SPEED * 2;
    public static final int RADIUS = 4;
    private int x,y;
    private int speed = DEFAULT_SPEED;
    private int dir;
    private int atk;
    private Color color;
    private boolean visible = true;

    public Bullet(int x, int y, int dir, int atk, Color color) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.atk = atk;
        this.color = color;
    }

    //给对象池使用，属性为默认值
    public Bullet() {
    }

    //绘制
    public void draw(Graphics g) {
        if (!visible) {
            return;
        }
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
                if (y < 0) {
                    visible = false;
                }
                break;
            case Tank.DIR_DOWN:
                y += speed;
                if (y > Constant.FRAME_HEIGHT) {
                    visible = false;
                }
                break;
            case Tank.DIR_LEFT:
                x -= speed;
                if (x < 0) {
                    visible = false;
                }
                break;
            case Tank.DIR_RIGHT:
                x += speed;
                if (x > Constant.FRAME_WIDTH) {
                    visible = false;
                }
                break;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSpeed() {
        return speed;
    }

    public int getDir() {
        return dir;
    }

    public int getAtk() {
        return atk;
    }

    public Color getColor() {
        return color;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
