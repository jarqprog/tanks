package com.tanksDevs.system.pojo;

import com.tanksDevs.system.entity.Genre;

public abstract class AbstractEntityPojo implements EntityPojo {

    private int id;
    private double X;
    private double Y;
    private double size;
    private Genre genre;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public double getX() {
        return X;
    }

    @Override
    public double getY() {
        return Y;
    }

    @Override
    public double getSize() {
        return size;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void setX(double x) {
        X = x;
    }

    @Override
    public void setY(double y) {
        Y = y;
    }

    @Override
    public void setSize(double size) {
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
