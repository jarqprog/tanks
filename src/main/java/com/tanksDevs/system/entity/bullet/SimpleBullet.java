package com.tanksDevs.system.entity.bullet;

import com.tanksDevs.system.entity.Colliding;
import com.tanksDevs.system.entity.Destructible;
import com.tanksDevs.system.entity.Direction;
import com.tanksDevs.system.entity.hitBox.HitBox;

public class SimpleBullet implements Bullet {

    @Override
    public void destroy(Destructible target) {

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
    public double getSpeed() {
        return 0;
    }

    @Override
    public Direction getDirection() {
        return null;
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
    public int getId() {
        return 0;
    }

    @Override
    public double getX() {
        return 0;
    }

    @Override
    public double getY() {
        return 0;
    }
}
