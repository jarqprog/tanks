package com.tanksDevs.system.player;

import com.tanksDevs.system.entity.tank.TankPojo;

public class UserPojo implements PlayerPojo{

    private TankPojo tankPojo;
    private String name;
    private int score;
    private boolean isReady;

    @Override
    public TankPojo getTankPojo() {
        return tankPojo;
    }

    @Override
    public void setTankPojo(TankPojo tankPojo) {
        this.tankPojo = tankPojo;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
