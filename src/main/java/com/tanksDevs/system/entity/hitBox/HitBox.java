package com.tanksDevs.system.entity.hitBox;

public interface HitBox {

    boolean checkCollision(HitBox other);
    int getBottomX();
    int getBottomY();
    int getTopX();
    int getTopY();
}
