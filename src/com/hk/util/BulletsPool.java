package com.hk.util;

import com.hk.game.Bullet;

import java.util.ArrayList;
import java.util.List;

//子弹对象池
public class BulletsPool {
    public static final int DEFAULT_POOL_SIZE = 200;
    public static final int POOL_MAX_SIZE = 300;
    //用于保存所有子弹的容器
    private static List<Bullet> pool = new ArrayList<>();

    static {
        for (int i=0; i<DEFAULT_POOL_SIZE; i++) {
            pool.add(new Bullet());
        }
    }

    //从池中获取一个子弹对象
    public static Bullet get() {
        Bullet bullet = null;

        if (pool.size() == 0) {     //池为空
            bullet = new Bullet();
        } else {
            bullet = pool.remove(0);
        }
        return bullet;
    }

    //归还子弹对象
    public static void theReturn(Bullet bullet) {
        if (pool.size() == POOL_MAX_SIZE) {
            return;
        }
        pool.add(bullet);
    }
}
