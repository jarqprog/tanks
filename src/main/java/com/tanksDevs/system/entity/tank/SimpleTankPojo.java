package com.tanksDevs.system.entity.tank;

import com.tanksDevs.system.pojo.AbstractEntityPojo;

public class SimpleTankPojo extends AbstractEntityPojo implements TankPojo {

    private int hp;
    private boolean isOccupied;

    public SimpleTankPojo() {}

    @Override
    public int getHp() {
        return hp;
    }

    @Override
    public void setHp(int hp) {
        this.hp = hp;
    }

    @Override
    public boolean getIsOccupied() {
        return isOccupied;
    }

    @Override
    public void setIsOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }
}
