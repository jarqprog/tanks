package com.tanksDevs.network.gameServer;

import com.tanksDevs.network.gameServer.InOut.ServerIn;
import com.tanksDevs.network.gameServer.InOut.ServerOut;
import com.tanksDevs.network.parser.PojoParser;
import com.tanksDevs.system.board.Board;
import com.tanksDevs.system.game.Game;

public interface ServerSuplier {

    ServerIn createReciever(int tcpPort, int udpPort);
    ServerOut createSender(int tcpPort, int udpPort);
    String getIpAddress();
    int getPortTCP();
    int getPortUDP();
    Board getBoard();  // todo - not necessary?
    Game getGame();
    PojoParser getParser();
}
