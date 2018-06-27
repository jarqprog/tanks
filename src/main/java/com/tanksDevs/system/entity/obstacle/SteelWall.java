package com.tanksDevs.system.entity.obstacle;

import com.tanksDevs.system.entity.AbstractEntity;
import com.tanksDevs.system.entity.Colliding;
import com.tanksDevs.system.entity.Genre;
import com.tanksDevs.system.entity.hitBox.BasicHitBox;
import com.tanksDevs.system.entity.hitBox.HitBox;

public class SteelWall extends AbstractEntity implements SolidWall {

    private HitBox hitBox;
    private final Genre genre = Genre.STEELWALL;

    public SteelWall(int id, double x, double y, double size) {
        super(id, x, y, size);
        this.hitBox = new BasicHitBox(x, y, size);
    }

    public SteelWall(SolidWallPojo solidWallPojo){
        super(solidWallPojo.getId(), solidWallPojo.getX(), solidWallPojo.getY(), solidWallPojo.getSize());
        this.hitBox = new BasicHitBox(solidWallPojo.getX(), solidWallPojo.getY(), solidWallPojo.getSize());
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
