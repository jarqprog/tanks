package com.tanksDevs.network.gameServer;

import com.esotericsoftware.kryonet.Server;
import com.tanksDevs.network.gameServer.InOut.ServerIn;
import com.tanksDevs.network.gameServer.InOut.ServerOut;
import com.tanksDevs.network.kryoHelper.KryoRegister;
import com.tanksDevs.network.parser.PojoParser;
import com.tanksDevs.system.board.Board;
import com.tanksDevs.system.game.Game;

public interface ServerSupplier {

    ServerIn createReceiver(Server server);
    ServerOut createSender(Server server);
    int getPortTCP();
    int getPortUDP();
    String getIpAddress();
    Board getBoard();  // todo - not necessary?
    Game getGame();
    PojoParser getParser();
    void setGame(Game game);
    void setBoard(Board board);    // todo - not necessary?
    KryoRegister getKryoRegister();
    int getLargeTimeWindow();
    int getShortTimeWindow();
}
