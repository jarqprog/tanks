package com.tanksDevs.system.entity.bullet;

import com.tanksDevs.system.entity.Colliding;
import com.tanksDevs.system.entity.Destructible;
import com.tanksDevs.system.entity.Direction;
import com.tanksDevs.system.entity.hitBox.HitBox;

public class SimpleBullet implements Bullet {

    private double x;
    private double y;
    private Direction direction;
    private double speed;

    public SimpleBullet() {

    }

    public SimpleBullet(double x, double y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.speed = 1.0;
    }

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
        return speed;
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public void move(Direction direction) {
        this.direction = direction;
        switch (direction) {
            case NORTH:
                y -= speed;
                break;
            case SOUTH:
                y += speed;
                break;
            case EAST:
                x += speed;
                break;
            case WEST:
                x -= speed;
                break;
        }
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
