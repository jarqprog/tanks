package com.tanksDevs.system.player;

import com.tanksDevs.system.entity.tank.TankPojo;

public interface PlayerPojo {
    TankPojo getTankPojo();
    void setTankPojo(TankPojo tankpojo);
    int getScore();
    void setScore(int score);
    String getName();
    void setName(String name);
    boolean isReady();
    void setReady(boolean ready);
}
