package com.tanksDevs.network.gameServer.InOut;

import com.tanksDevs.network.states.LocalState;

public interface ServerIn extends Runnable {

    LocalState getLocalState();

}
