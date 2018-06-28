package com.tanksDevs.network.gameServer.InOut;

import com.tanksDevs.network.states.LocalState;
import com.tanksDevs.system.game.Game;

public interface ServerIn extends Runnable {

    LocalState getLocalState();
    Game getGame();
    void stop();
    void stopPreparation();

}
