package com.tanksDevs.network.gameServer.InOut;

import com.tanksDevs.network.states.GlobalState;
import com.tanksDevs.system.game.Game;

public interface ServerOut extends Runnable {

    void put(GlobalState globalState);
    void put(Game game);

}
