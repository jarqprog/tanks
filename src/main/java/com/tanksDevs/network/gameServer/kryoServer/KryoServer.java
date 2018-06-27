package com.tanksDevs.network.gameServer.kryoServer;

import com.esotericsoftware.kryonet.Server;
import com.tanksDevs.network.gameClient.ClientSupplier;
import com.tanksDevs.network.gameClient.InOut.ClientIn;
import com.tanksDevs.network.gameClient.InOut.ClientOut;
import com.tanksDevs.network.gameServer.GameServer;
import com.tanksDevs.network.gameServer.InOut.ServerIn;
import com.tanksDevs.network.gameServer.InOut.ServerOut;
import com.tanksDevs.network.gameServer.ServerSuplier;
import com.tanksDevs.network.parser.PojoParser;
import com.tanksDevs.system.game.Game;
import com.tanksDevs.system.player.Player;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Set;

public class KryoServer implements GameServer {

    private final PojoParser parser;
    private final int portTCP;
    private final int portUDP;
    private final String ipAddress;
    private final Server server;
    private final ServerSuplier serverSuplier;
    private final Set<Player> players;
    private final Game game;
    private ServerIn serverIn;
    private ServerOut serverOut;

    public KryoServer(ServerSuplier serverSuplier, Set<Player> players) throws UnknownHostException {
        this.parser = serverSuplier.getParser();
        this.portTCP = serverSuplier.getPortTCP();
        this.portUDP = serverSuplier.getPortUDP();
        InetAddress ip = InetAddress.getLocalHost();
        this.ipAddress = ip.getHostAddress();
        this.serverSuplier = serverSuplier;
        this.server = new Server();
        this.players = new HashSet<>();
        this.game = serverSuplier.getGame();
    }


    @Override
    public void runServer() {

        run();

    }

    @Override
    public String getAddress() {
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
    public void run() {

    }

    private void initializeInOut() {




    }


    private void gameLoop() {


    }
}
