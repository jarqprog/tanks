package com.tanksDevs.system.entity.hitBox;

public class BasicHitBox implements HitBox {

    private int bottomX;
    private int bottomY;
    private int topX;
    private int topY;
    private int side;

    public BasicHitBox(int bottomX, int bottomY, int side) {
        this.bottomX = bottomX;
        this.bottomY = bottomY;
        this.side = side;
        calculatePeaks();
    }

    public int getBottomX() {
        return bottomX;
    }

    public int getBottomY() {
        return bottomY;
    }

    public int getTopX() {
        return topX;
    }

    public int getTopY() {
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
}


