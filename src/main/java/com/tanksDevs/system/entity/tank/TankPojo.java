package com.tanksDevs.system.entity.tank;

import com.tanksDevs.system.pojo.EntityPojo;

public interface TankPojo extends EntityPojo {

    int getHp();
    void setHp(int hp);
    boolean getIsOccupied();
    void setIsOccupied(boolean isOccupied);

}
