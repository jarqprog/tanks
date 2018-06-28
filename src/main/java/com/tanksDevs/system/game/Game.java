package com.tanksDevs.system.game;

import com.tanksDevs.system.board.Board;
import com.tanksDevs.system.entity.Colliding;
import com.tanksDevs.system.entity.tank.Tank;
import com.tanksDevs.system.player.Player;

import java.util.Set;

public interface Game {

    int getWidth();
    int getHeight();
    int getTileSize();
    Board getBoard();
    Set<Colliding> getCollidings();
    Set<Tank> getTanks();
    boolean markPlayerTank(Tank tank);
    boolean registerPlayer(Player player);
    Set<Player> getPlayers();
    void setTanks(Set<Tank> tanks);
    void setCollidings(Set<Colliding> collidings);
    // todo potrzeba wymyślić co z bazą!

}
