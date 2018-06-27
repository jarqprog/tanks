package com.tanksDevs.network.gameClient.InOut;

import com.tanksDevs.system.game.Game;
import com.tanksDevs.system.pojo.LocalState;

public interface ClientOut extends Runnable {

    void putState(LocalState localState);
    void updateGame(Game game);

}
