package com.tanksDevs.system.entity.tank;

import com.tanksDevs.system.entity.Direction;
import com.tanksDevs.system.entity.eagle.TankBasePojo;
import com.tanksDevs.system.pojo.EntityPojo;

public interface TankPojo extends EntityPojo {

    Direction getDirection();
    void setDirection(Direction direction);
    double getSpeed();
    void  setSpeed(double speed);
    int getHp();
    void setHp(int hp);
    boolean getIsOccupied();
    void setIsOccupied(boolean isOccupied);
    TankBasePojo getTankBasePojo();
    void setTankBasePojo(TankBasePojo tankBasePojo);
}
