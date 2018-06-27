package com.tanksDevs.system.entity.obstacle;

import com.tanksDevs.system.entity.AbstractEntity;
import com.tanksDevs.system.entity.Colliding;
import com.tanksDevs.system.entity.hitBox.HitBox;

public class SteelWall extends AbstractEntity implements Colliding {

    public SteelWall(int id, int x, int y, int size) {
        super(id, x, y, size);
    }

    @Override
    public boolean isCollision(Colliding other) { //Todo implement collision
        return false;
    }

    @Override
    public HitBox getHitBox() {
        return null;
    }
}
