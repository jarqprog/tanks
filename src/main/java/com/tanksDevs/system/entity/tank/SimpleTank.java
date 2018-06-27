package com.tanksDevs.system.entity.tank;

import com.tanksDevs.system.entity.AbstractEntity;
import com.tanksDevs.system.entity.Colliding;
import com.tanksDevs.system.entity.Direction;
import com.tanksDevs.system.entity.Genre;
import com.tanksDevs.system.entity.hitBox.BasicHitBox;
import com.tanksDevs.system.entity.hitBox.HitBox;

public class SimpleTank extends AbstractEntity implements Tank {

    private HitBox hitBox;
    private final Genre genre;

    public SimpleTank(int id, double x, double y, double size) {
        super(id, x, y, size);
        this.hitBox = new BasicHitBox(x, y, size);
        this.genre = Genre.TANK;
    }

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
        return hitBox.checkCollision(other.getHitBox());
    }

    @Override
    public HitBox getHitBox() {
        return hitBox;
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
    public int cooldown() {
        return 0;
    }

    @Override
    public boolean hasPlayer() {
        return false;
    }

    @Override
    public void markAsOccupied() {

    }

    @Override
    public Genre getGenre() {
        return genre;
    }
}
