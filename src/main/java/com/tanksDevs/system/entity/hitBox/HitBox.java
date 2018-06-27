package com.tanksDevs.system.entity.hitBox;

public interface HitBox {

    boolean checkCollision(HitBox other);
    double getBottomX();
    double getBottomY();
    double getTopX();
    double getTopY();
}