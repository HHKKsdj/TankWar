package com.hk.tank;

import com.hk.game.GameFrame;
import com.hk.util.Constant;
import com.hk.util.EnemyTanksPool;
import com.hk.util.MyUtil;

import java.awt.*;

public class EnemyTank extends Tank {
    private static Image[] enemyImg;
    //AI5秒计时开始时间
    private long AITime;

    static {
        enemyImg = new Image[4];
        enemyImg[0] = Toolkit.getDefaultToolkit().createImage("res/enemy_up.png");
        enemyImg[1] = Toolkit.getDefaultToolkit().createImage("res/enemy_down.png");
        enemyImg[2] = Toolkit.getDefaultToolkit().createImage("res/enemy_left.png");
        enemyImg[3] = Toolkit.getDefaultToolkit().createImage("res/enemy_right.png");
    }

    private EnemyTank(int x, int y, int dir) {
        super(x, y, dir);
        //AI计时
        AITime = System.currentTimeMillis();
    }

    public EnemyTank(){
        AITime = System.currentTimeMillis();
    }

    public static Tank createEnemy() {
        int x = MyUtil.getRandomNumber(0,2) == 0 ? RADIUS :
                Constant.FRAME_WIDTH - RADIUS;
        int y = GameFrame.titleBarH + RADIUS;
        int dir = DIR_DOWN;
//        Tank enemy = new EnemyTank(x,y,dir);
        Tank enemy = EnemyTanksPool.get();
        enemy.setX(x);
        enemy.setY(y);
        enemy.setDir(dir);
        enemy.setEnemy(true);
        enemy.setState(STATE_MOVE);
        enemy.setHp(DEFAULT_HP);
        return enemy;
    }

    public void drawImgTank(Graphics g) {
        g.drawImage(enemyImg[getDir()],getX()-RADIUS,getY()-RADIUS,null );
        AI();
    }

    //AI
    private void AI() {
        if (System.currentTimeMillis() - AITime > Constant.ENEMY_AI_INTERVAL) {
            //间隔5秒随机一个状态
            setState(MyUtil.getRandomNumber(0,2) == 0 ? STATE_STAND : STATE_MOVE);
            setDir(MyUtil.getRandomNumber(DIR_UP,DIR_RIGHT+1));
            AITime = System.currentTimeMillis();
        }
        //小概率开火控制
        if (Math.random() < Constant.ENEMY_FIRE_PERCENT) {
            fire();
        }
    }

}
