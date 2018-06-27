package com.tanksDevs.system.entity.tank;

import com.tanksDevs.system.entity.Colliding;
import com.tanksDevs.system.entity.Direction;
import com.tanksDevs.system.entity.hitBox.HitBox;

public class PlayerTank implements Tank {

//    private static final int ID;

    private double x;
    private double y;
    private int size;
    private double speed;
    private Direction direction;
    private boolean moving;

    public PlayerTank(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.size = 17;
        this.speed = 3.0;
        this.direction = direction;
        this.moving = false;
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
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
}
