package com.tanksDevs.network.states;

import com.tanksDevs.network.input.UserInput;

public interface LocalState {

    UserInput getUserInput();
    int getTankId();
    void setUserInput(UserInput userInput);
    void setTankId(int tankId);

}
