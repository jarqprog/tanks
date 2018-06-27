package com.tanksDevs.system.pojo;

import com.tanksDevs.system.entity.Genre;

public abstract class AbstractEntityPojo implements EntityPojo {

    private int id;
    private int X;
    private int Y;
    private int size;
    private Genre genre;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getX() {
        return X;
    }

    @Override
    public int getY() {
        return Y;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void setX(int x) {
        X = x;
    }

    @Override
    public void setY(int y) {
        Y = y;
    }

    @Override
    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public Genre getGenre() {
        return genre;
    }

    @Override
    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
