package com.tanksDevs.system.game;

import com.tanksDevs.system.entity.tank.TankPojo;
import com.tanksDevs.system.player.PlayerPojo;
import com.tanksDevs.system.pojo.EntityPojo;

import java.util.HashSet;
import java.util.Set;

public class SimpleGamePojo implements GamePojo {


    private Set<EntityPojo> collidings = new HashSet<>();
    private Set<TankPojo> tanks = new HashSet<>();
    private Set<PlayerPojo> players = new HashSet<>();


    public Set<EntityPojo> getCollidings() {
        return collidings;
    }

    public void setCollidings(Set<EntityPojo> collidings) {
        this.collidings = collidings;
    }

    public Set<TankPojo> getTanks() {
        return tanks;
    }

    public void setTanks(Set<TankPojo> tanks) {
        this.tanks = tanks;
    }

    public Set<PlayerPojo> getPlayers() {
        return players;
    }

    public void setPlayers(Set<PlayerPojo> players) {
        this.players = players;
    }


}

