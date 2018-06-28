package com.tanksDevs.network.gameClient;

import com.esotericsoftware.kryonet.Client;
import com.tanksDevs.network.gameServer.GameServer;
import com.tanksDevs.network.gameClient.InOut.ClientIn;
import com.tanksDevs.network.gameClient.InOut.ClientOut;
import com.tanksDevs.network.kryoHelper.KryoRegister;
import com.tanksDevs.network.parser.PojoParser;
import com.tanksDevs.system.player.Player;

public interface ClientSupplier {

    Player getPlayer();
    GameServer getGameServer();
    ClientIn createReceiver(Client client);
    ClientOut createSender(Client client);
    boolean hasServer();
    void registerServer(GameServer gameServer);
    int getPortTCP();
    int getPortUDP();
    PojoParser getParser();
    String getIpAddress();
    KryoRegister getKryoRegister();
    int getLargeTimeWindow();
    int getShortTimeWindow();

}
