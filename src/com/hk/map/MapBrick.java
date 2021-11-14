package com.hk.map;

import com.hk.game.Bullet;
import com.hk.util.BulletsPool;
import com.hk.util.MyUtil;

import java.awt.*;
import java.util.List;

public class MapBrick {
    public static final int TYPE_NORMAL = 0;
    public static final int TYPE_HOME = 1;
    public static final int TYPE_COVER = 2;
    public static final int TYPE_HARD = 3;
//    private static Image brickImg;
    public static int brickW = 50;
    public static int radius = brickW/2;
    public int type = TYPE_NORMAL;

    private static Image[] brickImg;

    static {
        brickImg = new Image[4];
        brickImg[TYPE_NORMAL] = Toolkit.getDefaultToolkit().createImage("res/brick.png");
        brickImg[TYPE_HOME] = Toolkit.getDefaultToolkit().createImage("res/home.png");
        brickImg[TYPE_COVER] = Toolkit.getDefaultToolkit().createImage("res/cover.png");
        brickImg[TYPE_HARD] = Toolkit.getDefaultToolkit().createImage("res/hard.png");
        if (brickW<=0){
            brickW = brickImg[TYPE_NORMAL].getWidth(null);
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
            brickW = brickImg[TYPE_NORMAL].getWidth(null);
        }
    }
    public void draw(Graphics g) {
        if (!visible) {
            return;
        }
        if (brickW<=0){
            brickW = brickImg[TYPE_NORMAL].getWidth(null);
        }
        g.drawImage(brickImg[type],x,y,null);
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

    public boolean isHome(){
        return type == TYPE_HOME;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
