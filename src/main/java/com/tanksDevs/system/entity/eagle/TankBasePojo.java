package com.tanksDevs.system.entity.eagle;

import com.tanksDevs.system.pojo.EntityPojo;

public interface TankBasePojo extends EntityPojo {
    int getHp();

    void setHp(int hp);

    void setPlayerOwner(int owner);

    int getPlayerOwner();
}
