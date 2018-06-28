package com.tanksDevs.system.entity;

public interface Entity {

    int getId();
    double getX();
    double getY();
    double getSize();
    void setX(double x);
    void setY(double y);
    void setSize(double size);
    Genre getGenre();
}
