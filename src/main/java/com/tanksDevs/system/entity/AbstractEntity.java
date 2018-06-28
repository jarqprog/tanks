package com.tanksDevs.system.entity;

public abstract class AbstractEntity implements Entity {
    private int id;
    private double x;
    private double y;
    private double size;

    public AbstractEntity() {
    }

    public AbstractEntity(int id) {
        this.id = id;
    }

    public AbstractEntity(int id, double x, double y, double size) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.size = size;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public double getSize() {
        return size;
    }

    @Override
    public void setX(double x) {
        this.x = x;
    }

    @Override
    public void setY(double y) {
        this.y = y;
    }

    @Override
    public void setSize(double size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "id=" + id + " " + getGenre().toString();
    }
}
