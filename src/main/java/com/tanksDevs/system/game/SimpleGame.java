package com.tanksDevs.system.game;

import com.tanksDevs.system.board.Board;
import com.tanksDevs.system.entity.Colliding;
import com.tanksDevs.system.entity.tank.Tank;
import com.tanksDevs.system.player.Player;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SimpleGame implements Game {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int TILE_SIZE = 40;
    private Set<Colliding> collidings;
    private Set<Tank> tanks;
    private Set<Player> players;
  
    public SimpleGame(Set<Colliding> collidings, Set<Tank> tanks) {
          this.collidings = collidings;
          this.tanks = tanks;
          this.players = new HashSet<>();
      }

     public SimpleGame(Set<Colliding> collidings, Set<Tank> tanks, Set<Player> players) {
          this(collidings, tanks);
          this.players = players;
      }

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }

    @Override
    public int getTileSize() {
        return TILE_SIZE;
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

      for(Iterator<Tank> it = tanks.iterator(); it.hasNext(); ) {
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
