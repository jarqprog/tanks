package com.tanksDevs.system.entity.obstacle;

import com.tanksDevs.system.pojo.AbstractEntityPojo;

public class BrickWallPojo extends AbstractEntityPojo implements DestroyableWallPojo{

    private int hp;

    @Override
    public int getHp() {
        return hp;
    }

    @Override
    public void setHp(int hp) {
        this.hp = hp;
    }

}
