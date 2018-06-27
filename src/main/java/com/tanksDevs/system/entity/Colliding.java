package com.tanksDevs.system.entity;

import com.tanksDevs.system.entity.hitBox.HitBox;

public interface Colliding{

    boolean isCollision(Colliding other);
    HitBox getHitBox();
}
