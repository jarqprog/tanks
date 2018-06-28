package com.tanksDevs.network.states;

import com.tanksDevs.network.input.UserInput;

public class TankState implements LocalState {

    private UserInput userInput;
    private int tankId;

    public TankState() { }

    @Override
    public UserInput getUserInput() {
        return userInput;
    }

    @Override
    public int getTankId() {
        return tankId;
    }

    @Override
    public void setUserInput(UserInput userInput) {
        this.userInput = userInput;
    }

    @Override
    public void setTankId(int tankId) {
        this.tankId = tankId;
    }

}
