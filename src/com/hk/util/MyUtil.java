package com.hk.util;

import java.awt.*;

/**
 * 工具类
 */
public class MyUtil {
    private MyUtil(){}

    /**
     * 得到指定的区间的随机数
     * @param min 区间最小值，包含
     * @param max 区间最大值，不包含
     * @return 随机数
     */
    public static final int getRandomNumber(int min,int max){
        return (int)(Math.random()*(max-min)+min);
    }

    /**
     * 得到随机的颜色
     * @return
     */
    public static final Color getRandomColor(){
        int red=getRandomNumber(0,256);
        int blue=getRandomNumber(0,256);
        int green=getRandomNumber(0,256);
        Color c=new Color(red,green,blue);
        return c;
    }

    //钜形与点碰撞检测
    public  static final boolean isCollide(int rectX, int rectY, int radius, int pointX, int pointY) {
        int disX =Math.abs(rectX - pointX);
        int disY =Math.abs(rectY - pointY);
        if (disX < radius && disY < radius) {
            return true;
        } else {
            return false;
        }
    }

    private static final String[] NAME = {
            "A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"
    };
    public static final String getRandomName () {
        return NAME[getRandomNumber(0,NAME.length)];
    }
}
