package com.tanksDevs.system.entity.eagle;

import com.tanksDevs.system.pojo.AbstractEntityPojo;

public class EaglePojo extends AbstractEntityPojo implements TankBasePojo {

    private int hp;
    private int playerOwner;

    public EaglePojo() {
    }

    @Override
    public int getHp() {
        return hp;
    }

    @Override
    public void setHp(int hp) {
        this.hp = hp;
    }

    @Override
    public int getPlayerOwner() {
        return playerOwner;
    }

    @Override
    public void setPlayerOwner(int playerOwner) {
        this.playerOwner = playerOwner;
    }
}