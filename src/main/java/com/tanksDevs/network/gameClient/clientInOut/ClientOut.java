package com.tanksDevs.network.helpers;

import com.tanksDevs.system.game.Game;
import com.tanksDevs.system.pojo.LocalState;

public interface ClientOut extends Runnable {

    void putState(LocalState localState);
    void updateGame(Game game);

}
