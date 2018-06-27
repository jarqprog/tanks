package com.tanksDevs.network.gameServer;

public interface GameServer extends Runnable {

    void runServer();
    void getPort();
    void getAddress();

}
