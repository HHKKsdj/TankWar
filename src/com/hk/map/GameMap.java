package com.hk.map;

import com.hk.game.GameFrame;
import com.hk.tank.Tank;
import com.hk.util.Constant;
import com.hk.util.MapBrickPool;
import com.hk.util.MyUtil;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameMap {
    public static final int MAP_X = Tank.RADIUS*3;
    public static final int MAP_Y = Tank.RADIUS*3;
    public static final int MAP_WIGHT = Constant.FRAME_WIDTH - Tank.RADIUS*6;
    public static final int MAP_HEIGHT = Constant.FRAME_HEIGHT - Tank.RADIUS*8 - GameFrame.titleBarH;


    //地图块容器
    private List<MapBrick> bricks = new ArrayList<>();

    //大本营
    private Home home;

    public GameMap() {
        initMap();
    }

    //初始化
    private void initMap() {
//        final int COUNT = 20;
//        for (int i = 0; i < COUNT; i++) {
//            MapBrick brick = MapBrickPool.get();
//            int x = MyUtil.getRandomNumber(MAP_X,MAP_X+MAP_WIGHT-MapBrick.brickW);
//            int y = MyUtil.getRandomNumber(MAP_Y,MAP_Y+MAP_HEIGHT-MapBrick.brickW);
//            if (isCollide(bricks,x,y)){
//                i--;
//                continue;
//            }
//            brick.setX(x);
//            brick.setY(y);
//            bricks.add(brick);
//        }

        addRow(MAP_X,MAP_Y,MAP_X+MAP_WIGHT,MapBrick.TYPE_NORMAL,0);
        addRow(MAP_X,MAP_Y+MapBrick.brickW*2,MAP_X+MAP_WIGHT,MapBrick.TYPE_COVER,0);
        addRow(MAP_X,MAP_Y+MapBrick.brickW*4,MAP_X+MAP_WIGHT,MapBrick.TYPE_HARD,MapBrick.brickW);

        home = new Home();
        addHome();
    }

    private void addHome(){
        bricks.addAll(home.getBricks());
    }

    //判断地图快是否重合
    private boolean isCollide(List<MapBrick> bricks, int x, int y) {
        for (MapBrick brick : bricks) {
            int brikeX = brick.getX();
            int brikeY = brick.getY();
            if (Math.abs(brikeX-x)<MapBrick.brickW&&Math.abs(brikeY-y)<MapBrick.brickW){
                return true;
            }
        }
        return false;
    }

    public void draw(Graphics g) {
        for (MapBrick brick : bricks) {
            brick.draw(g);
        }

    }

    public void clearDestoryBrick(){
        for (int i = 0; i < bricks.size(); i++) {
            MapBrick brick = bricks.get(i);
            if (!brick.isVisible()){
                bricks.remove(i);
            }
        }
    }

    //往地图块容器中添加一行指定类型的地图块
    public void addRow(int startX,int startY,int endX,int type,final int DIS){
        int count = (endX-startX)/(MapBrick.brickW+DIS);
        for (int i = 0; i < count; i++) {
            MapBrick brick = MapBrickPool.get();
            brick.setType(type);
            brick.setX(startX+i*(MapBrick.brickW+DIS));
            brick.setY(startY);
            bricks.add(brick);
        }
    }

    public List<MapBrick> getBricks() {
        return bricks;
    }


}
