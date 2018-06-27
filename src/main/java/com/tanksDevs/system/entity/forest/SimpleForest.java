package com.tanksDevs.system.entity.forest;

import com.tanksDevs.system.entity.AbstractEntity;
import com.tanksDevs.system.entity.Colliding;
import com.tanksDevs.system.entity.hitBox.HitBox;

public class SimpleForest extends AbstractEntity implements Forest {

    public SimpleForest(int id, int x, int y, int size) {
        super(id, x, y, size);
    }

    @Override
    public boolean isCollision(Colliding other) { //Todo collision handling
        return false;
    }

    @Override
    public HitBox getHitBox() {
        return null;
    }
}
