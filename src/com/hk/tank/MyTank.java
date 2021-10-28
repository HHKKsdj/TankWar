package com.hk.tank;

import java.awt.*;

public class MyTank extends Tank{
    //贴图
    private static Image[] tankImg;
    static {
        tankImg = new Image[4];
        tankImg[0] = Toolkit.getDefaultToolkit().createImage("res/tank_up.png");
        tankImg[1] = Toolkit.getDefaultToolkit().createImage("res/tank_down.png");
        tankImg[2] = Toolkit.getDefaultToolkit().createImage("res/tank_left.png");
        tankImg[3] = Toolkit.getDefaultToolkit().createImage("res/tank_right.png");
    }
    public MyTank (int x, int y, int dir) {
        super(x,y,dir);
    }

    @Override
    public void drawImgTank(Graphics g) {
        g.drawImage(tankImg[getDir()],getX()-RADIUS,getY()-RADIUS,null );
    }
}
