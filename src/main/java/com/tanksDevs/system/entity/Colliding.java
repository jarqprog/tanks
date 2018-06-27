package com.tanksDevs.system.entity;

import com.tanksDevs.system.entity.hitBox.HitBox;

public interface Colliding extends Entity {

    boolean isCollision(Colliding other);
    HitBox getHitBox();
}
