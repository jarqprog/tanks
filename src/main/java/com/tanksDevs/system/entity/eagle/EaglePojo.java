package com.tanksDevs.system.entity.eagle;

import com.tanksDevs.system.pojo.AbstractEntityPojo;

public class EaglePojo extends AbstractEntityPojo implements TankBasePojo {

    private int hp;

    public EaglePojo() {}

    @Override
    public int getHp() {
        return hp;
    }

    @Override
    public void setHp(int hp) {
        this.hp = hp;
    }
}