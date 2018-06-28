package com.tanksDevs.system.entity.bullet;

import com.tanksDevs.system.entity.*;
import com.tanksDevs.system.entity.hitBox.HitBox;

public class SimpleBullet extends AbstractEntity implements Bullet {

    private final Genre genre = Genre.BULLET;

    public SimpleBullet(int id) {
        super(id);
    }

    public SimpleBullet(BulletPojo bulletPojo) {
        super(bulletPojo.getId());
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
    public Genre getGenre() {
        return genre;
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
        return false;
    }

    @Override
    public HitBox getHitBox() {
        return null;
    }

}
