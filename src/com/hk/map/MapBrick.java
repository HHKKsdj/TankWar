package com.hk.map;

import com.hk.game.Bullet;
import com.hk.util.BulletsPool;
import com.hk.util.MyUtil;

import java.awt.*;
import java.util.List;

public class MapBrick {
    private static Image brickImg;
    public static int brickW = 40;
    public static int radius = brickW/2;
    public static int brickH;

    static {
        brickImg = Toolkit.getDefaultToolkit().createImage("res/brick.png");
        if (brickW<=0){
            brickW = brickImg.getWidth(null);
        }
    }

    private int x,y;
    private boolean visible = true;

    public MapBrick() {

    }

    public MapBrick(int x, int y){
        this.x = x;
        this.y = y;
        if (brickW<=0){
            brickW = brickImg.getWidth(null);
        }
    }
    public void draw(Graphics g) {
        if (!visible) {
            return;
        }
        if (brickW<=0){
            brickW = brickImg.getWidth(null);
        }
        g.drawImage(brickImg,x,y,null);
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

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    //子弹碰撞检测
    public boolean isCollideBullet(List<Bullet> bullets){
        if (!visible) {
            return false;
        }
        for (Bullet bullet : bullets) {
            int bulletX = bullet.getX();
            int bulletY = bullet.getY();
            boolean collide = MyUtil.isCollide(x + radius, y + radius, radius, bulletX, bulletY);
            if (collide) {
                bullet.setVisible(false);
                BulletsPool.theReturn(bullet);
                return true;
            }
        }
        return false;
    }
}
