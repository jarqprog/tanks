package com.tanksDevs.network.gameServer;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Server;
import com.tanksDevs.network.gameServer.InOut.ServerIn;
import com.tanksDevs.network.gameServer.InOut.ServerOut;
import com.tanksDevs.network.parser.PojoParser;
import com.tanksDevs.system.board.Board;
import com.tanksDevs.system.game.Game;

public interface ServerSupplier {

    ServerIn createReciever(int tcpPort, int udpPort, String ipAddress, Kryo kryo,  Server server);
    ServerOut createSender(int tcpPort, int udpPort, String ipAddress, Kryo kryo, Server server);
    int getPortTCP();
    int getPortUDP();
    Board getBoard();  // todo - not necessary?
    Game getGame();
    PojoParser getParser();
}
