package com.tanksDevs.system.pojo;

import com.tanksDevs.system.entity.Genre;

public interface EntityPojo {

    int getId();
    double getX();
    double getY();
    double getSize();
    Genre getGenre();

    void setId(int id);
    void setX(double X);
    void setY(double Y);
    void setSize(double size);
    void setGenre(Genre genre);

}
