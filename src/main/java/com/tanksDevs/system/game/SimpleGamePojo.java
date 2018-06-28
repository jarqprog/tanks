package com.tanksDevs.system.game;

import com.tanksDevs.network.parser.NaivePojoParser;
import com.tanksDevs.network.parser.PojoParser;
import com.tanksDevs.system.entity.Colliding;
import com.tanksDevs.system.entity.tank.Tank;
import com.tanksDevs.system.entity.tank.TankPojo;
import com.tanksDevs.system.player.Player;
import com.tanksDevs.system.player.PlayerPojo;
import com.tanksDevs.system.pojo.EntityPojo;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SimpleGamePojo implements GamePojo {


    private Set<EntityPojo> collidings = new HashSet<>();
    private Set<TankPojo> tanks = new HashSet<>();
    private Set<PlayerPojo> players = new HashSet<>();

    private PojoParser parser = new NaivePojoParser();


    public Set<EntityPojo> getCollidings() {
        return collidings;
    }

    @Override
    public Set<Colliding> gatherCollidings() {

        Set<Colliding> toReturn = new HashSet<>();

        Iterator<EntityPojo> iterator = collidings.iterator();
        while ( iterator.hasNext() ) {
            toReturn.add(parser.parse(iterator.next()));
        }

        return toReturn;
    }

    public void setCollidings(Set<EntityPojo> collidings) {
        this.collidings = collidings;
    }

    public Set<TankPojo> getTanks() {
        return tanks;
    }

    @Override
    public Set<Tank> gatherTanks() {

        Set<Tank> toReturn = new HashSet<>();

        Iterator<TankPojo> iterator = tanks.iterator();
        while ( iterator.hasNext() ) {
            toReturn.add(parser.parse(iterator.next()));
        }
        return toReturn;
    }

    public void setTanks(Set<TankPojo> tanks) {
        this.tanks = tanks;
    }

    public Set<PlayerPojo> getPlayers() {
        return players;
    }

    @Override
    public Set<Player> gatherPlayers() {
        Set<Player> toReturn = new HashSet<>();

        Iterator<PlayerPojo> iterator = players.iterator();
        while ( iterator.hasNext() ) {
            toReturn.add(parser.parse(iterator.next()));
        }
        return toReturn;
    }

    public void setPlayers(Set<PlayerPojo> players) {
        this.players = players;
    }


}

