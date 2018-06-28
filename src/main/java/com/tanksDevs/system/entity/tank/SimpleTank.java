package com.tanksDevs.system.entity.tank;

import com.tanksDevs.system.entity.AbstractEntity;
import com.tanksDevs.system.entity.Colliding;
import com.tanksDevs.system.entity.Direction;
import com.tanksDevs.system.entity.Genre;
import com.tanksDevs.system.entity.eagle.Eagle;
import com.tanksDevs.system.entity.eagle.TankBase;
import com.tanksDevs.system.entity.hitBox.BasicHitBox;
import com.tanksDevs.system.entity.hitBox.HitBox;

public class SimpleTank extends AbstractEntity implements Tank {

    private double speed;
    private int hp;
    private boolean destroyed;
    private HitBox hitBox;
    private Direction direction;
    private final Genre genre = Genre.TANK;
    private boolean isOccupied;
    private TankBase tankBase;

    public SimpleTank(int id, double x, double y, double size, Direction direction, TankBase tankBase) {
        super(id, x, y, size);
        this.direction = direction;
        this.speed = 3.0;
        this.hp = 5;
        this.destroyed = false;
        this.hitBox = new BasicHitBox(x, y, size);
//        this.tankBase = tankBase;
    }

    public SimpleTank(TankPojo tankPojo) {
        super(tankPojo.getId(), tankPojo.getX(), tankPojo.getY(), tankPojo.getSize());
        this.direction = tankPojo.getDirection();
        this.speed = tankPojo.getSpeed();
        this.hp = tankPojo.getHp();
        this.destroyed = (hp <= 0);
        this.isOccupied = tankPojo.getIsOccupied();
        this.hitBox = new BasicHitBox(tankPojo.getX(), tankPojo.getY(), tankPojo.getSize());
//        this.tankBase = new Eagle(tankPojo.getTankBasePojo());
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
                setY( getY() - speed);
                break;
            case SOUTH:
                setY( getY() + speed);
                break;
            case EAST:
                setX( getX() + speed);
                break;
            case WEST:
                setX( getX() - speed);
                break;
        }
    }

    @Override
    public void stop() {

    }

    public boolean isCollision(Colliding other) {
        return hitBox.checkCollision(other.getHitBox());
    }

    @Override
    public HitBox getHitBox() {
        return hitBox;
    }

    @Override
    public int getHp() {
        return hp;
    }

    @Override
    public void decrementHp(int hitPoints) {
        hp -= hitPoints;
        if(hp <= 0){
            destroyed = true;
        }
    }

    @Override
    public boolean isDestroyed() {
        return destroyed;
    }

    @Override
    public int cooldown() {
        return 0;
    }

    @Override
    public boolean hasPlayer() {
        return isOccupied;
    }

    @Override
    public void markAsOccupied() {
        this.isOccupied = true;
    }

    @Override
    public TankBase getBase() {
        return this.tankBase;
    }

    @Override
    public Genre getGenre() {
        return genre;
    }
}
