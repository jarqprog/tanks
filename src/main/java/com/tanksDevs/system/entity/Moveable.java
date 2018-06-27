package com.tanksDevs.system.entity;

public interface Moveable extends Colliding {

    int getSpeed();
    void move(Direction direction);
    void stop();

}
