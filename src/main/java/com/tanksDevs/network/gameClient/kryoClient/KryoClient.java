package com.tanksDevs.network.gameClient.kryoClient;

import com.tanksDevs.network.gameClient.GameClient;
import com.tanksDevs.network.gameServer.GameServer;
import com.tanksDevs.network.gameClient.InOut.ClientIn;
import com.tanksDevs.network.gameClient.InOut.ClientOut;
import com.tanksDevs.system.game.Game;
import com.tanksDevs.system.player.Player;

public class KryoClient implements GameClient {

    // use reference to java fx

    private final int portTCP;
    private final int portUDP;
    private final String ipAddress;
    private final ClientSupplier clientSupplier;
    private final GameServer server;
    private final Player player;
    private ClientIn clientIn;
    private ClientOut clientOut;
    private Game game;

    public KryoClient(ClientSupplier clientSupplier) {
        this.portTCP = clientSupplier.getPortTCP();
        this.portUDP = clientSupplier.getPortUDP();
        this.ipAddress = clientSupplier.getIpAddress();
        this.clientSupplier = clientSupplier;
        this.player = clientSupplier.getPlayer();
        this.server = clientSupplier.getServer();
    }

    @Override
    public void runClient() {

    }


    private void setupInOut() {


    }
}
