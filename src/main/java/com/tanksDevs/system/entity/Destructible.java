package com.tanksDevs.system.entity;

public interface Destructible extends Entity {

    int getHp();
    void decrementHp(int hitPoints);
    boolean isDestroyed();
}
