package com.tanksDevs.network.gameServer;

import com.tanksDevs.network.input.UserInput;
import com.tanksDevs.network.states.LocalState;

public interface GameServer extends Runnable {

    void runServer();
    String getAddress();
    int getPortTCP();
    int getPortUDP();

    class TankState implements LocalState {

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
}
