package com.tanksDevs.network.gameClient.kryoClient;

import com.tanksDevs.network.gameServer.GameServer;
import com.tanksDevs.network.gameClient.InOut.ClientIn;
import com.tanksDevs.network.gameClient.InOut.ClientOut;
import com.tanksDevs.system.player.Player;

public interface ClientSupplier {

    Player getPlayer();
    GameServer getServer();
    ClientIn createReciever(String ipAddress, int tcpPort, int udpPort);
    ClientOut createSender(String ipAddress, int tcpPort, int udpPort);
    boolean hasServer();
    void registerServer();
    String getIpAddress();
    int getPortTCP();
    int getPortUDP();

}
