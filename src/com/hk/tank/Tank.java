package com.hk.tank;

import com.hk.game.Bullet;
import com.hk.game.Explode;
import com.hk.game.GameFrame;
import com.hk.util.BulletsPool;
import com.hk.util.Constant;
import com.hk.util.ExplodesPool;
import com.hk.util.MyUtil;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 坦克类
 */
public abstract class Tank {

    //四个方向
    public static final int DIR_UP = 0;
    public static final int DIR_DOWN = 1;
    public static final int DIR_LEFT = 2;
    public static final int DIR_RIGHT = 3;
    //半径
    public static final int RADIUS = 20;
    //默认速度/帧
    public static final int DEFAULT_SPEED = 5;
    //坦克的状态
    public static final int STATE_STAND = 0;
    public static final int STATE_MOVE = 1;
    public static final int STATE_DIE = 2;
    //坦克的初始生命
    public static final int DEFAULT_HP = 1000;

    private int x, y;

    private int hp;
    private int atk;
    private int speed = DEFAULT_SPEED;
    private int dir;
    private int state = STATE_STAND;
    private Color color;
    private boolean isEnemy = false;
    //炮弹
    private List<Bullet> bullets = new ArrayList<>();
    //爆炸效果
    private List<Explode> explodes = new ArrayList<>();

    public Tank(int x, int y, int dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        color = MyUtil.getRandomColor();
    }



    /**
     * |
     * 绘制坦克
     *
     * @param g
     */
    public void draw(Graphics g) {
        g.setColor(color);
        logic();
        drawImgTank(g);
        drawBullets(g);
    }

    //使用图片绘制坦克
    public abstract void drawImgTank (Graphics g);

/**
    //使用系统方式绘制坦克
    private void drawTank (Graphics g) {
        //绘制坦克的圆
        g.fillOval(x - RADIUS, y - RADIUS, RADIUS << 1, RADIUS << 1);
        int endX = x, endY = y;
        switch (dir) {
            case DIR_UP:
                endY = y - RADIUS * 2;
                g.drawLine(x - 1, y, endX - 1, endY);
                g.drawLine(x + 1, y, endX + 1, endY);
                break;
            case DIR_DOWN:
                endY = y + RADIUS * 2;
                g.drawLine(x - 1, y, endX - 1, endY);
                g.drawLine(x + 1, y, endX + 1, endY);
                break;
            case DIR_LEFT:
                endX = x - RADIUS * 2;
                g.drawLine(x, y - 1, endX, endY - 1);
                g.drawLine(x, y + 1, endX, endY + 1);
                break;
            case DIR_RIGHT:
                endX = x + RADIUS * 2;
                g.drawLine(x, y - 1, endX, endY - 1);
                g.drawLine(x, y + 1, endX, endY + 1);
                break;
        }
        g.drawLine(x, y, endX, endY);
    }
*/

    //坦克逻辑处理
    private void logic() {
        switch (state) {
            case STATE_STAND:
                break;
            case STATE_MOVE:
                move();
                break;
            case STATE_DIE:
                break;
        }
    }

    //坦克移动功能
    private void move () {
        switch (dir) {
            case DIR_UP:
                y -= speed;
                if (y < RADIUS + GameFrame.titleBarH) {
                    y = RADIUS + GameFrame.titleBarH;
                }
                break;
            case DIR_DOWN:
                y += speed;
                if (y > Constant.FRAME_HEIGHT - RADIUS) {
                    y = Constant.FRAME_HEIGHT - RADIUS;
                }
                break;
            case DIR_LEFT:
                x -= speed;
                if (x < RADIUS) {
                    x = RADIUS;
                }
                break;
            case DIR_RIGHT:
                x += speed;
                if (x > Constant.FRAME_WIDTH - RADIUS) {
                    x = Constant.FRAME_WIDTH - RADIUS;
                }
                break;
        }
    }

    //开火
    public void fire() {
        int bulletX = x ;
        int bulletY = y ;
        switch (dir) {
            case DIR_UP:
                bulletY -= RADIUS;
                break;
            case DIR_DOWN:
                bulletY += RADIUS;
                break;
            case DIR_LEFT:
                bulletX -= RADIUS;
                break;
            case DIR_RIGHT:
                bulletX += RADIUS;
                break;
        }

        Bullet bullet = BulletsPool.get();
        bullet.setX(bulletX);
        bullet.setY(bulletY);
        bullet.setDir(dir);
        bullet.setSpeed(speed);
        bullet.setAtk(atk);
        bullet.setColor(color);
        bullet.setVisible(true);
        //Bullet bullet = new Bullet(bulletX,bulletY,dir,atk,color);
        bullets.add(bullet);
    }

    //绘制当前坦克发射的所有子弹
    private void drawBullets (Graphics g) {
        for (Bullet bullet : bullets) {
            bullet.draw(g);
        }
        //移除不可见子弹
        for (int i=0; i< bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            if (!bullet.isVisible()) {
                Bullet remove = bullets.remove(i);
                BulletsPool.theReturn(remove);
                i--;
            }
        }
    }

    //坦克与子弹碰撞
    public void collideBullets (List<Bullet> bullets) {
        for (Bullet bullet : bullets) {
            int bulletX = bullet.getX();
            int bulletY = bullet.getY();
            if (MyUtil.isCollide(x,y,RADIUS,bulletX,bulletY)) {
                bullet.setVisible(false);

                Explode explode = ExplodesPool.get();
                explode.setX(x);
                explode.setY(y+RADIUS);
                explode.setVisible(true);
                explode.setIndex(0);
                explodes.add(explode);
            }
        }
    }

    //绘制爆炸
    public void drawExplodes (Graphics g) {
        for (Explode explode : explodes) {
            explode.draw(g);
        }
        //将不可见的爆炸效果归还
        for (int i = 0; i < explodes.size(); i++) {
            Explode explode = explodes.get(i);
            if (!explode.isVisible()) {
                Explode remove = explodes.remove(i);
                ExplodesPool.theReturn(remove);
                i--;
            }
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isEnemy() {
        return isEnemy;
    }

    public void setEnemy(boolean enemy) {
        isEnemy = enemy;
    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(List<Bullet> bullets) {
        this.bullets = bullets;
    }
}
