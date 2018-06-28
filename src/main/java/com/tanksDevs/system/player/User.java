package com.tanksDevs.system.player;

import com.tanksDevs.system.entity.tank.SimpleTank;
import com.tanksDevs.system.entity.tank.Tank;

public class User implements Player{
    private Tank tank;
    private String name;
    private int score;
    private boolean isReady;

    public User(String name, Tank tank) {
        this.tank = tank;
        this.name = name;
        this.score = 0;
        this.isReady = false;
    }

    public User(PlayerPojo playerPojo){
        this.tank = new SimpleTank(playerPojo.getTankPojo());
        this.name = playerPojo.getName();
        this.score = playerPojo.getScore();
        this.isReady = playerPojo.isReady();
    }

    @Override
    public Tank getTank() {
        return tank;
    }

    @Override
    public void setTank(Tank tank) {
        this.tank = tank;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public boolean isReady() {
        return isReady;
    }

    @Override
    public void setReady(boolean ready) {
        isReady = ready;
    }


}
