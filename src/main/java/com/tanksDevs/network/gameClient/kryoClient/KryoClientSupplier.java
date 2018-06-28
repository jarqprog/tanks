package com.tanksDevs.network.gameClient.kryoClient;

import com.esotericsoftware.kryonet.Client;
import com.tanksDevs.network.gameClient.ClientSupplier;
import com.tanksDevs.network.gameClient.InOut.ClientIn;
import com.tanksDevs.network.gameClient.InOut.ClientOut;
import com.tanksDevs.network.gameServer.GameServer;
import com.tanksDevs.network.parser.PojoParser;
import com.tanksDevs.system.player.Player;

public class KryoClientSupplier implements ClientSupplier {

    private final int portTCP;
    private final int portUDP;
    private final String ipAddress;
    private final PojoParser pojoParser;
    private final Player player;
    private GameServer gameServer = null;

    public KryoClientSupplier(int portTCP, int portUDP, String ipAddress, PojoParser pojoParser,
                              Player player) {
        this.portTCP = portTCP;
        this.portUDP = portUDP;
        this.ipAddress = ipAddress;
        this.pojoParser = pojoParser;
        this.player = player;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public GameServer getGameServer() {
        return gameServer;
    }

    @Override
    public ClientIn createReceiver(Client client) {
        return KryoClientIn.getInstance(client, pojoParser);
    }

    @Override
    public ClientOut createSender(Client client) {
        return KryoClientOut.getInstance(client, pojoParser);
    }

    @Override
    public boolean hasServer() {
        return gameServer != null;
    }

    @Override
    public void registerServer(GameServer gameServer) {
        this.gameServer = gameServer;
    }

    @Override
    public String getIpAddress() {
        return ipAddress;
    }

    @Override
    public int getPortTCP() {
        return portTCP;
    }

    @Override
    public int getPortUDP() {
        return portUDP;
    }

    @Override
    public PojoParser getParser() {
        return pojoParser;
    }
}
