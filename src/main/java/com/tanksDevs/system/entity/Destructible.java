package com.tanksDevs.system.entity;

public interface Destructible{

    int getHp();
    void decrementHp(int hitPoints);
    boolean isDestroyed();
}
