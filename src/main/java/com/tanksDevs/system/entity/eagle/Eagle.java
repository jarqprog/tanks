package com.tanksDevs.system.entity.eagle;

import com.tanksDevs.system.entity.AbstractEntity;
import com.tanksDevs.system.entity.Colliding;
import com.tanksDevs.system.entity.Genre;
import com.tanksDevs.system.entity.hitBox.HitBox;

public class Eagle extends AbstractEntity implements TankBase {

    private int hp;
    private boolean destroyed;
    private final Genre genre;

    public Eagle(int id, int x, int y, int size) {
        super(id, x, y, size);
        this.hp = 10;
        this.genre = Genre.EAGLE;
    }

    @Override
    public boolean isCollision(Colliding other) { //Todo collision handling
        return false;
    }

    @Override
    public HitBox getHitBox() {
        return null;
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
}
