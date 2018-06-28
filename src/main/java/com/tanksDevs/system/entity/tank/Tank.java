package com.tanksDevs.system.entity.tank;

import com.tanksDevs.system.entity.Destructible;
import com.tanksDevs.system.entity.Moveable;
import com.tanksDevs.system.entity.eagle.TankBase;

public interface Tank extends Moveable, Destructible {

    int cooldown();
    boolean hasPlayer();
    void markAsOccupied();
    TankBase getBase();
}
