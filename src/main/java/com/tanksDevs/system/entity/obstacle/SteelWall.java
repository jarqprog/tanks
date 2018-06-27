package com.tanksDevs.system.entity.obstacle;

import com.tanksDevs.system.entity.AbstractEntity;
import com.tanksDevs.system.entity.Colliding;
import com.tanksDevs.system.entity.Genre;
import com.tanksDevs.system.entity.hitBox.HitBox;

public class SteelWall extends AbstractEntity implements Colliding {

    private final Genre genre;

    public SteelWall(int id, int x, int y, int size) {
        super(id, x, y, size);
        this.genre = Genre.STEELWALL;
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
    public Genre getGenre() {
        return genre;
    }
}
