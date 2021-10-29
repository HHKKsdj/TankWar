package com.hk.game;

import java.awt.*;

public class Explode {
    public static final int EXPLODE_FRAME_COUNT = 9;
    public static Image[] img;
    private static int explodeWidth;
    private static int explodeHeight;
    static {
        img = new Image[EXPLODE_FRAME_COUNT/3];
        for (int i = 0; i < img.length; i++) {
            img[i] = Toolkit.getDefaultToolkit().createImage("res/bomb_"+i+".png");
        }

    }

    //爆炸效果属性
    private int x,y;
    private int index = 0;
    private boolean visible = true;

    public Explode(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Explode() {
    }

    public void draw (Graphics g) {
        if (!visible) {
            return;
        }
        if (explodeHeight <= 0) {
            explodeHeight = img[0].getHeight(null);
            explodeWidth = img[0].getWidth(null);
        }

        g.drawImage(img[index/3], x-explodeWidth/2, y-explodeHeight, null);
        index++;
        if (index >= EXPLODE_FRAME_COUNT) {
            visible = false;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static Image[] getImg() {
        return img;
    }

    public static void setImg(Image[] img) {
        Explode.img = img;
    }

    public static int getExplodeWidth() {
        return explodeWidth;
    }

    public static void setExplodeWidth(int explodeWidth) {
        Explode.explodeWidth = explodeWidth;
    }

    public static int getExplodeHeight() {
        return explodeHeight;
    }

    public static void setExplodeHeight(int explodeHeight) {
        Explode.explodeHeight = explodeHeight;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

}
