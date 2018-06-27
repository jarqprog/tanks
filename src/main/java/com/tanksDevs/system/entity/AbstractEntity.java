package com.tanksDevs.system.entity;

public abstract class AbstractEntity implements Entity {
    private int id;
    private double x;
    private double y;
    private double size;

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
    public String toString() {
        return getClass().getSimpleName() +
                "id=" + id + " " + getGenre().toString();
    }
}
