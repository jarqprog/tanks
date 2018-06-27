package com.tanksDevs.system.player;

import com.tanksDevs.system.entity.tank.Tank;

public class User implements Player{
    private Tank tank;
    private String name;
    private int score;
    private boolean isReady;

    public User(String name) { // Todo creating tanks
        this.name = name;
        this.score = 0;
        this.isReady = false;
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
