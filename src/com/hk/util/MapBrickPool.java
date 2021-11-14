package com.hk.util;

import com.hk.game.Bullet;
import com.hk.map.MapBrick;

import java.util.ArrayList;
import java.util.List;

public class MapBrickPool {
    public static final int DEFAULT_POOL_SIZE = 50;
    public static final int POOL_MAX_SIZE = 70;
    //用于保存所有子弹的容器
    private static List<MapBrick> pool = new ArrayList<>();

    static {
        for (int i=0; i<DEFAULT_POOL_SIZE; i++) {
            pool.add(new MapBrick());
        }
    }

    //从池中获取一个子弹对象
    public static MapBrick get() {
        MapBrick mapBrick = null;

        if (pool.size() == 0) {     //池为空
            mapBrick = new MapBrick();
        } else {
            mapBrick = pool.remove(0);
        }
        return mapBrick;
    }

    //归还子弹对象
    public static void theReturn(MapBrick mapBrick) {
        if (pool.size() == POOL_MAX_SIZE) {
            return;
        }
        pool.add(mapBrick);
    }
}
