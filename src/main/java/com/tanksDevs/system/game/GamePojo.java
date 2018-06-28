package com.tanksDevs.system.game;

import com.tanksDevs.system.entity.tank.TankPojo;
import com.tanksDevs.system.player.PlayerPojo;
import com.tanksDevs.system.pojo.EntityPojo;

import java.util.Set;

public interface GamePojo {

    Set<EntityPojo> getCollidings();

    void setCollidings(Set<EntityPojo> collidings);

    Set<TankPojo> getTanks();

    void setTanks(Set<TankPojo> tanks);

    Set<PlayerPojo> getPlayers();

    void setPlayers(Set<PlayerPojo> players);



}
