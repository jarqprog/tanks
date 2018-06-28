package com.tanksDevs.network.gameClient.InOut;

import com.tanksDevs.system.game.Game;
import com.tanksDevs.network.states.LocalState;

public interface ClientOut extends Runnable {

    void putLocalState(LocalState localState);
    void putGame(Game game);
    void stop();
    void stopPreparation();

}
