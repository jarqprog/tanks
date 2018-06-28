package com.tanksDevs.system.entity.hitBox;

public interface HitBox {

    boolean checkCollision(HitBox other);
    double getBottomX();
    double getBottomY();
    double getTopX();
    double getTopY();
    void setBottomX(double bottomX);
    void setBottomY(double bottomY);
    void setTopX(double topX);
    void setTopY(double topY);
    void setSide(double side);
}
