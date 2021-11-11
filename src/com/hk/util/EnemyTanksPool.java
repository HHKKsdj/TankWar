package com.hk.util;

import com.hk.tank.EnemyTank;
import com.hk.tank.Tank;

import java.util.ArrayList;
import java.util.List;

public class EnemyTanksPool {

    public static final int DEFAULT_POOL_SIZE = 20;
    public static final int POOL_MAX_SIZE = 20;
    //用于保存所有子弹的容器
    private static List<Tank> pool = new ArrayList<>();

    static {
        for (int i=0; i<DEFAULT_POOL_SIZE; i++) {
            pool.add(new EnemyTank());
        }
    }

    //从池中获取一个对象
    public static Tank get() {
        Tank tank = null;

        if (pool.size() == 0) {     //池为空
            tank = new EnemyTank();
        } else {
            tank = pool.remove(0);
        }
        return tank;
    }

    //归还子弹对象
    public static void theReturn(Tank tank) {
        if (pool.size() == POOL_MAX_SIZE) {
            return;
        }
        pool.add(tank);
    }
}
