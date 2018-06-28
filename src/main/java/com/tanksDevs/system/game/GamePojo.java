package com.tanksDevs.system.game;

import com.tanksDevs.system.entity.Colliding;
import com.tanksDevs.system.entity.tank.Tank;
import com.tanksDevs.system.entity.tank.TankPojo;
import com.tanksDevs.system.player.Player;
import com.tanksDevs.system.player.PlayerPojo;
import com.tanksDevs.system.pojo.EntityPojo;

import java.util.Set;

public interface GamePojo {

    Set<EntityPojo> getCollidings();
    Set<Colliding> gatherCollidings();

    void setCollidings(Set<EntityPojo> collidings);

    Set<TankPojo> getTanks();
    Set<Tank> gatherTanks();

    void setTanks(Set<TankPojo> tanks);

    Set<PlayerPojo> getPlayers();
    Set<Player> gatherPlayers();

    void setPlayers(Set<PlayerPojo> players);



}
