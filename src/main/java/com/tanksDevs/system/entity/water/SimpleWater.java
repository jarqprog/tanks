package com.tanksDevs.system.entity.water;

import com.tanksDevs.system.entity.AbstractEntity;
import com.tanksDevs.system.entity.Colliding;
import com.tanksDevs.system.entity.Genre;
import com.tanksDevs.system.entity.hitBox.BasicHitBox;
import com.tanksDevs.system.entity.hitBox.HitBox;

public class SimpleWater extends AbstractEntity implements Water {

    private HitBox hitBox;
    private Genre genre = Genre.WATER;

    public SimpleWater(int id, double x, double y, double size) {
        super(id, x, y, size);
        this.hitBox = new BasicHitBox(x, y, size);
    }

    public SimpleWater(WaterPojo waterPojo){
        super(waterPojo.getId(), waterPojo.getX(), waterPojo.getY(), waterPojo.getSize());
        this.hitBox = new BasicHitBox(waterPojo.getX(), waterPojo.getY(), waterPojo.getSize());
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
