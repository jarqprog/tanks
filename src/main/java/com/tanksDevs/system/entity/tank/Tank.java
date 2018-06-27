package com.tanksDevs.system.entity.tank;

import com.tanksDevs.system.entity.Destructible;
import com.tanksDevs.system.entity.Moveable;

public interface Tank extends Moveable, Destructible {
    int cooldown();
}
