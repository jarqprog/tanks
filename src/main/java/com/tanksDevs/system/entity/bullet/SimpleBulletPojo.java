package com.tanksDevs.system.entity.bullet;

import com.tanksDevs.system.pojo.AbstractEntityPojo;

public class SimpleBulletPojo extends AbstractEntityPojo implements BulletPojo {

    private int hp;

    public SimpleBulletPojo() {}

    @Override
    public int getHp() {
        return hp;
    }

    @Override
    public void setHp(int hp) {
        this.hp = hp;
    }
}
