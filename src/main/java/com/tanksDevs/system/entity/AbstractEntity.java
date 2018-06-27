package com.tanksDevs.system.entity;

public abstract class AbstractEntity implements Entity {
    private int id;
    private int x;
    private int y;
    private int size;

    public AbstractEntity(int id) {
        this.id = id;
    }

    public AbstractEntity(int id, int x, int y, int size) {
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
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getSize() {
        return size;
    }
}
