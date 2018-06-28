package com.tanksDevs.system.game;

import com.tanksDevs.system.board.Board;
import com.tanksDevs.system.entity.Colliding;
import com.tanksDevs.system.entity.tank.Tank;
import com.tanksDevs.system.player.Player;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

// naive implementation! tmp
public class SimpleGame implements Game {

    private Set<Colliding> collidings = new HashSet<>();
    private Set<Tank> tanks = new HashSet<>();
    private Set<Player> players = new HashSet<>();

    public SimpleGame(Set<Colliding> collidings, Set<Tank> tanks) {
        this.collidings = collidings;
        this.tanks = tanks;
    }

    public SimpleGame(GamePojo gamePojo) {
        this.collidings = gamePojo.gatherCollidings();
        this.tanks = gamePojo.gatherTanks();
        this.players = gamePojo.gatherPlayers();
    }

    @Override
    public Board getBoard() {
        return null;
    }

    @Override
    public Set<Colliding> getCollidings() {
        return collidings;
    }

    @Override
    public Set<Tank> getTanks() {
        return tanks;
    }

    @Override
    public boolean markPlayerTank(Tank tank) {

        for( Iterator<Tank> it = tanks.iterator(); it.hasNext(); ) {
            Tank toMark = it.next();
            if (toMark.equals(tank)) {
                toMark.markAsOccupied();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean registerPlayer(Player player) {
        return players.add(player);
    }

    @Override
    public Set<Player> getPlayers() {
        return this.players;
    }

    @Override
    public void setCollidings(Set<Colliding> collidings) {
        this.collidings = collidings;
    }

    @Override
    public void setTanks(Set<Tank> tanks) {
        this.tanks = tanks;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
