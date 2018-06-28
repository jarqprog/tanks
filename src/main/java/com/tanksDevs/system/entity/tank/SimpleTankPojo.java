package com.tanksDevs.system.entity.tank;

import com.tanksDevs.system.entity.Direction;
import com.tanksDevs.system.entity.eagle.TankBasePojo;
import com.tanksDevs.system.pojo.AbstractEntityPojo;

public class SimpleTankPojo extends AbstractEntityPojo implements TankPojo {

    private double speed;
    private Direction direction;
    private int hp;
    private boolean isOccupied;
    private TankBasePojo tankBasePojo;
    private int playerOwner;

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public double getSpeed() {
        return speed;
    }

    @Override
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    @Override
    public int getHp() {
        return hp;
    }

    @Override
    public void setHp(int hp) {
        this.hp = hp;
    }

    @Override
    public boolean getIsOccupied() {
        return isOccupied;
    }

    @Override
    public void setIsOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }

    @Override
    public void setPlayerOwner(int playerOwner) {
        this.playerOwner = playerOwner;
    }

    @Override
    public int getPlayerOwner() {
        return playerOwner;
    }

    @Override
    public TankBasePojo getTankBasePojo() {
        return tankBasePojo;
    }

    @Override
    public void setTankBasePojo(TankBasePojo tankBasePojo) {
        this.tankBasePojo = tankBasePojo;
    }
}
