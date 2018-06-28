package com.tanksDevs.system.board.tiles;

import com.tanksDevs.system.pojo.EntityPojo;

public interface Cell {
    Type getTileType();

    int getX();

    int getY();

    EntityPojo parseToEntityPojo();
}
