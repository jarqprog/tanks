package com.tanksDevs.system.pojo;

import com.tanksDevs.network.states.LocalState;
import com.tanksDevs.network.input.UserInput;

public class TankState implements LocalState {

    private UserInput userInput;
    private int tankId;

    public TankState() { }

    @Override
    public UserInput getUserInput() {
        return null;
    }

    @Override
    public int getTankId() {
        return 0;
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
