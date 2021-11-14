package com.hk.map;

import com.hk.util.Constant;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Home {
    public static final int HOME_X = (Constant.FRAME_WIDTH-3*MapBrick.brickW)/2;
    public static final int HOME_Y = Constant.FRAME_HEIGHT-2*MapBrick.brickW;

    private List<MapBrick> bricks = new ArrayList<>();
    public Home() {
        bricks.add(new MapBrick(HOME_X,HOME_Y));
        bricks.add(new MapBrick(HOME_X,HOME_Y+MapBrick.brickW));
        bricks.add(new MapBrick(HOME_X+MapBrick.brickW,HOME_Y));
        bricks.add(new MapBrick(HOME_X+MapBrick.brickW*2,HOME_Y));
        bricks.add(new MapBrick(HOME_X+MapBrick.brickW*2,HOME_Y+MapBrick.brickW));
        //老巢
        bricks.add(new MapBrick(HOME_X+MapBrick.brickW,HOME_Y+MapBrick.brickW));
        bricks.get(bricks.size()-1).setType(MapBrick.TYPE_HOME);
    }

    public void draw(Graphics g){
        for (MapBrick brick : bricks) {
            brick.draw(g);
        }
    }

    public List<MapBrick> getBricks() {
        return bricks;
    }
}
