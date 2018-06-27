package com.tanksDevs.system.entity.tank;

import com.tanksDevs.system.entity.Colliding;
import com.tanksDevs.system.entity.Direction;
import com.tanksDevs.system.entity.TankBase;
import com.tanksDevs.system.entity.hitBox.HitBox;

public class SimpleTank implements Tank {


    @Override
    public int getSpeed() {
        return 0;
    }

    @Override
    public void move(Direction direction) {

    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isCollision(Colliding other) {
        return false;
    }

    @Override
    public HitBox getHitBox() {
        return null;
    }

    @Override
    public int getHp() {
        return 0;
    }

    @Override
    public void decrementHp(int hitPoints) {

    }

    @Override
    public boolean isDestroyed() {
        return false;
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public int getX() {
        return 0;
    }

    @Override
    public int getY() {
        return 0;
    }

    @Override
    public TankBase getBase() {
        return null;
    }
}
