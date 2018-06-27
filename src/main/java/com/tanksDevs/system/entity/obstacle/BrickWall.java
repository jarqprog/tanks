package com.tanksDevs.system.entity.obstacle;

import com.tanksDevs.system.entity.AbstractEntity;
import com.tanksDevs.system.entity.Colliding;
import com.tanksDevs.system.entity.Destructible;
import com.tanksDevs.system.entity.hitBox.HitBox;

public class BrickWall extends AbstractEntity implements Destructible, Colliding {

    private int hp;
    private boolean destroyed;

    public BrickWall(int id, int x, int y, int size) {
        super(id, x, y, size);
        this.hp = 1;
        this.destroyed = false;
    }

    @Override
    public boolean isCollision(Colliding other) { //Todo implement collision
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
}
