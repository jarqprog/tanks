package com.tanksDevs.network.gameServer.InOut;

import com.tanksDevs.network.states.LocalState;
import com.tanksDevs.system.game.Game;

import java.util.Collection;
import java.util.Queue;

public interface ServerIn extends Runnable {

    LocalState getLocalState();  // remove ONE state!
    Collection<LocalState> getLocalStates();  // removes ALL states!
    Game getGame();
    void stop();
    void stopPreparation();

}
