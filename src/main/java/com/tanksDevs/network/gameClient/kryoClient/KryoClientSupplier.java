package com.tanksDevs.network.gameClient.kryoClient;

import com.esotericsoftware.kryonet.Client;
import com.tanksDevs.network.gameClient.ClientSupplier;
import com.tanksDevs.network.gameClient.InOut.ClientIn;
import com.tanksDevs.network.gameClient.InOut.ClientOut;
import com.tanksDevs.network.gameServer.GameServer;
import com.tanksDevs.network.kryoHelper.KryoRegister;
import com.tanksDevs.network.parser.PojoParser;
import com.tanksDevs.system.player.Player;

public class KryoClientSupplier implements ClientSupplier {

    private final int portTCP;
    private final int portUDP;
    private final int largeTimeWindow;
    private final int shortTimeWindow;
    private final String ipAddress;
    private final PojoParser pojoParser;
    private final KryoRegister kryoRegister;
    private final Player player;
    private GameServer gameServer = null;

    public static ClientSupplier create(int portTCP, int portUDP,
                                        int largeTimeWindow, int shortTimeWindow,
                                        String ipAddress, PojoParser pojoParser,
                                        KryoRegister kryoRegister, Player player) {
        return new KryoClientSupplier(portTCP, portUDP, largeTimeWindow, shortTimeWindow,
                ipAddress, pojoParser, kryoRegister, player);
    }

    private KryoClientSupplier(int portTCP, int portUDP,
                              int largeTimeWindow, int shortTimeWindow,
                              String ipAddress, PojoParser pojoParser,
                              KryoRegister kryoRegister, Player player) {
        this.portTCP = portTCP;
        this.portUDP = portUDP;
        this.largeTimeWindow = largeTimeWindow;
        this.shortTimeWindow = shortTimeWindow;
        this.ipAddress = ipAddress;
        this.pojoParser = pojoParser;
        this.kryoRegister = kryoRegister;
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
        return KryoClientIn.getInstance(client, pojoParser, largeTimeWindow, shortTimeWindow);
    }

    @Override
    public ClientOut createSender(Client client) {
        return KryoClientOut.getInstance(client, pojoParser, largeTimeWindow, shortTimeWindow);
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

    @Override
    public KryoRegister getKryoRegister() {
        return kryoRegister;
    }

    @Override
    public int getLargeTimeWindow() {
        return largeTimeWindow;
    }

    @Override
    public int getShortTimeWindow() {
        return shortTimeWindow;
    }
}
