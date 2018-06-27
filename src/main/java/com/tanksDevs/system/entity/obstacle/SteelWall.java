package com.tanksDevs.system.entity.obstacle;

import com.tanksDevs.system.entity.AbstractEntity;
import com.tanksDevs.system.entity.Colliding;
import com.tanksDevs.system.entity.Genre;
import com.tanksDevs.system.entity.hitBox.BasicHitBox;
import com.tanksDevs.system.entity.hitBox.HitBox;

public class SteelWall extends AbstractEntity implements Colliding {

    private HitBox hitBox;
    private final Genre genre;

    public SteelWall(int id, int x, int y, int size) {
        super(id, x, y, size);
        this.hitBox = new BasicHitBox(x, y, size);
        this.genre = Genre.STEELWALL;
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
    public Genre getGenre() {
        return genre;
    }
}
