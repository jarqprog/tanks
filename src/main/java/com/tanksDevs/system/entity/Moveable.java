package com.tanksDevs.system.entity;

public interface Moveable extends Colliding {

    double getSpeed();
    Direction getDirection();
    void move(Direction direction);
    void stop();

}
