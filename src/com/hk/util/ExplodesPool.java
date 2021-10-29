package com.hk.util;

import com.hk.game.Bullet;
import com.hk.game.Explode;

import java.util.ArrayList;
import java.util.List;

public class ExplodesPool {
    public static final int DEFAULT_POOL_SIZE = 10;
    public static final int POOL_MAX_SIZE = 20;
    //用于保存所有爆炸的容器
    private static List<Explode> pool = new ArrayList<>();

    static {
        for (int i=0; i<DEFAULT_POOL_SIZE; i++) {
            pool.add(new Explode());
        }
    }

    //从池中获取一个爆炸对象
    public static Explode get() {
        Explode explode = null;

        if (pool.size() == 0) {     //池为空
            explode = new Explode();
        } else {
            explode = pool.remove(0);
        }
        return explode;
    }

    //归还爆炸对象
    public static void theReturn(Explode explode) {
        if (pool.size() == POOL_MAX_SIZE) {
            return;
        }
        pool.add(explode);
    }
}
