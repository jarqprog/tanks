package com.tanksDevs.system.game;

import com.tanksDevs.system.board.Board;
import com.tanksDevs.system.entity.Colliding;
import com.tanksDevs.system.entity.tank.Tank;
import com.tanksDevs.system.player.Player;

import java.util.Set;

public interface Game {

    Board getBoard();
    Set<Colliding> getColliding();
    Set<Tank> getTanks();
    boolean markPlayerTank(Tank tank);
    boolean registerPlayer(Player player);
    // todo potrzeba wymyślić co z bazą!

}
