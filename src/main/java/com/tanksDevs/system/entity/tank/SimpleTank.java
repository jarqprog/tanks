package com.tanksDevs.system.entity.tank;

import com.tanksDevs.system.entity.AbstractEntity;
import com.tanksDevs.system.entity.Colliding;
import com.tanksDevs.system.entity.Direction;
import com.tanksDevs.system.entity.Genre;
import com.tanksDevs.system.entity.hitBox.BasicHitBox;
import com.tanksDevs.system.entity.hitBox.HitBox;

public class SimpleTank extends AbstractEntity implements Tank {

    private int hp;
    private boolean destroyed;
    private HitBox hitBox;
    private final Genre genre = Genre.TANK;
    private boolean isOccupied;

    public SimpleTank(int id, double x, double y, double size) {
        super(id, x, y, size);
        this.hp = 5;
        this.destroyed = false;
        this.hitBox = new BasicHitBox(x, y, size);
    }

    public SimpleTank(TankPojo tankPojo) {
        super(tankPojo.getId(), tankPojo.getX(), tankPojo.getY(), tankPojo.getSize());
        this.hp = tankPojo.getHp();
        this.destroyed = (hp <= 0);
        this.isOccupied = tankPojo.getIsOccupied();
        this.hitBox = new BasicHitBox(tankPojo.getX(), tankPojo.getY(), tankPojo.getSize());
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
    public Genre getGenre() {
        return genre;
    }
}
