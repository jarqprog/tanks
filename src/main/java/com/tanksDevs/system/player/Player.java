package com.tanksDevs.system.player;

import com.tanksDevs.system.gameObject.tank.Tank;

public interface Player {
    Tank getTank();
    void setTank(Tank tank);
    int getScore();
    void setScore(int score);
    String getName();
    boolean isReady();
    void setReady(boolean ready);
}
