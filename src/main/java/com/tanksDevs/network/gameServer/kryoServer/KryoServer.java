package com.tanksDevs.network.gameServer.kryoServer;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Server;
import com.tanksDevs.network.gameServer.GameServer;
import com.tanksDevs.network.gameServer.InOut.ServerIn;
import com.tanksDevs.network.gameServer.InOut.ServerOut;
import com.tanksDevs.network.gameServer.ServerSupplier;
import com.tanksDevs.network.parser.PojoParser;
import com.tanksDevs.system.entity.forest.SimpleForest;
import com.tanksDevs.system.entity.forest.SimpleForestPojo;
import com.tanksDevs.system.game.Game;
import com.tanksDevs.system.game.SimpleGame;
import com.tanksDevs.system.player.Player;

import java.io.IOException;
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
    private final ServerSupplier serverSupplier;
    private final Set<Player> players;
    private final Game game;
    private final Kryo kryo;
    private ServerIn serverIn;
    private ServerOut serverOut;

    public KryoServer(ServerSupplier serverSupplier, Set<Player> players) throws UnknownHostException {
        this.parser = serverSupplier.getParser();
        this.portTCP = serverSupplier.getPortTCP();
        this.portUDP = serverSupplier.getPortUDP();
        InetAddress ip = InetAddress.getLocalHost();
        this.ipAddress = ip.getHostAddress();
        this.serverSupplier = serverSupplier;
        this.server = new Server();
        this.kryo = server.getKryo();
        this.players = new HashSet<>();
        this.game = serverSupplier.getGame();
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

    private void prepare() throws IOException {

        server.start();
        server.bind(portTCP, portUDP);

        kryo.register(SimpleForest.class);
        kryo.register(SimpleForestPojo.class);
        kryo.register(SimpleGame.class);


        serverIn = serverSupplier.createReciever(portTCP, portUDP, ipAddress, kryo, server);
        serverOut = serverSupplier.createSender(portTCP, portUDP, ipAddress, kryo, server);





    }


    private void gameLoop() {


    }
}
