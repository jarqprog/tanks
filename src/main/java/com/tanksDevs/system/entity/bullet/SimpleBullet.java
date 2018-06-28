package com.tanksDevs.system.entity.bullet;

import com.tanksDevs.system.entity.*;
import com.tanksDevs.system.entity.hitBox.HitBox;

public class SimpleBullet extends AbstractEntity implements Bullet {

    private Direction direction;
    private double speed;
    private int hp;
    private boolean destroyed;
    private HitBox hitBox;
    private final static int START_BULLET_HP = 1;
    private final static Genre genre = Genre.BULLET;

    public SimpleBullet() {

    }

    public SimpleBullet(int id, double x, double y, double size, Direction direction) {
        super(id, x, y, size);
        this.direction = direction;
        this.speed = 1.0;
    }

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
    public double getSpeed() {
        return speed;
    }

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

    @Override
    public boolean isCollision(Colliding other) {
        return hitBox.checkCollision(other.getHitBox());
    }

    @Override
    public HitBox getHitBox() {
        return hitBox;
    }
}
