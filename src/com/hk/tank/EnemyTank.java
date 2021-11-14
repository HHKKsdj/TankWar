package com.hk.tank;

import com.hk.game.GameFrame;
import com.hk.util.Constant;
import com.hk.util.EnemyTanksPool;
import com.hk.util.MyUtil;

import java.awt.*;

public class EnemyTank extends Tank {
    public static final int TYPE_GRAY = 0;
    public static final int TYPE_GREEN = 1;
    private int type = TYPE_GRAY;
    private static Image[] grayImg;
    private static Image[] greenImg;
    //AI5秒计时开始时间
    private long AITime;

    static {
        grayImg = new Image[4];
        grayImg[0] = Toolkit.getDefaultToolkit().createImage("res/enemy_up.png");
        grayImg[1] = Toolkit.getDefaultToolkit().createImage("res/enemy_down.png");
        grayImg[2] = Toolkit.getDefaultToolkit().createImage("res/enemy_left.png");
        grayImg[3] = Toolkit.getDefaultToolkit().createImage("res/enemy_right.png");

        greenImg = new Image[4];
        greenImg[0] = Toolkit.getDefaultToolkit().createImage("res/enemy2_up.png");
        greenImg[1] = Toolkit.getDefaultToolkit().createImage("res/enemy2_down.png");
        greenImg[2] = Toolkit.getDefaultToolkit().createImage("res/enemy2_left.png");
        greenImg[3] = Toolkit.getDefaultToolkit().createImage("res/enemy2_right.png");
    }

    private EnemyTank(int x, int y, int dir) {
        super(x, y, dir);
        //AI计时
        AITime = System.currentTimeMillis();
        type = MyUtil.getRandomNumber(0,2);
    }

    public EnemyTank(){
        type = MyUtil.getRandomNumber(0,2);
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
        if (type == TYPE_GRAY) {
            g.drawImage(grayImg[getDir()],getX()-RADIUS,getY()-RADIUS,null );
        } else {
            g.drawImage(greenImg[getDir()],getX()-RADIUS,getY()-RADIUS,null );
        }

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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
