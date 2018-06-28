package com.tanksDevs.system.entity.hitBox;

public class BasicHitBox implements HitBox {

    private double bottomX;
    private double bottomY;
    private double topX;
    private double topY;
    private double side;

//    public BasicHitBox() {}

    public BasicHitBox(double bottomX, double bottomY, double side) {
        this.bottomX = bottomX;
        this.bottomY = bottomY;
        this.side = side;
        calculatePeaks();
    }

    public double getBottomX() {
        return bottomX;
    }

    public double getBottomY() {
        return bottomY;
    }

    public double getTopX() {
        return topX;
    }

    public double getTopY() {
        return topY;
    }

    private void calculatePeaks() {
        topX = bottomX + side;
        topY = bottomY + side;
    }

    @Override
    public boolean checkCollision(HitBox other) {
        boolean result = true;
        if (bottomX > other.getTopX() || other.getBottomX() > topX || bottomY > other.getTopY() || other.getBottomY() > topY) {
            result = false;
        }
        return result;
    }

    @Override
    public void setBottomX(double bottomX) {
        this.bottomX = bottomX;
    }

    @Override
    public void setBottomY(double bottomY) {
        this.bottomY = bottomY;
    }

    @Override
    public void setTopX(double topX) {
        this.topX = topX;
    }

    @Override
    public void setTopY(double topY) {
        this.topY = topY;
    }

    @Override
    public void setSide(double side) {
        this.side = side;
    }
}
