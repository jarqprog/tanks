package com.tanksDevs.network.gameClient.InOut;

import com.tanksDevs.system.game.Game;
import com.tanksDevs.network.states.GlobalState;

public interface ClientIn extends Runnable {


    GlobalState getGlobalState();
    Game getGame();
    void stop();
    void stopPreparation();

}
