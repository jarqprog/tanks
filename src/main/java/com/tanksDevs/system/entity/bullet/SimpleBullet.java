package com.tanksDevs.system.entity.bullet;

import com.tanksDevs.system.entity.*;
import com.tanksDevs.system.entity.hitBox.HitBox;

public class SimpleBullet extends AbstractEntity implements Bullet {

    private int hp;
    private boolean destroyed;
    private HitBox hitBox;
    private final int START_BULLET_HP = 1;
    private final Genre genre = Genre.BULLET;

    public SimpleBullet(int id) {
        super(id);
        this.hp = START_BULLET_HP;
        destroyed = false;
    }

    public SimpleBullet(BulletPojo bulletPojo) {
        super(bulletPojo.getId());
        this.hp = bulletPojo.getHp();
        destroyed = (hp <= 0);
    }

    @Override
    public void destroy(Destructible target) {
        target.decrementHp(1);
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
        return hitBox.checkCollision(other.getHitBox());
    }

    @Override
    public HitBox getHitBox() {
        return hitBox;
    }

}
