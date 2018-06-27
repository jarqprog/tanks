package com.tanksDevs.system.pojo;

import com.tanksDevs.system.entity.Genre;

public interface EntityPojo {

    int getId();
    int getX();
    int getY();
    int getSize();
    Genre getGenre();

    void setId(int id);
    void setX(int X);
    void setY(int Y);
    void setSize(int size);
    void setGenre(Genre genre);

}
