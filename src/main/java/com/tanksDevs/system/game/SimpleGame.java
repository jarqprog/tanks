package com.tanksDevs.system.game;

import com.tanksDevs.system.board.Board;
import com.tanksDevs.system.entity.Colliding;
import com.tanksDevs.system.entity.tank.Tank;
import com.tanksDevs.system.player.Player;

import java.util.Set;

public class SimpleGame implements Game {

//    private


    @Override
    public Board getBoard() {
        return null;
    }

    @Override
    public Set<Colliding> getColliding() {
        return null;
    }

    @Override
    public Set<Tank> getTanks() {
        return null;
    }

    @Override
    public boolean markPlayerTank(Tank tank) {
        return false;
    }

    @Override
    public boolean registerPlayer(Player player) {
        return false;
    }
}
