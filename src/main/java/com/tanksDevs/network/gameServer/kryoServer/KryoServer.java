package com.tanksDevs.network.gameServer.kryoServer;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Server;
import com.tanksDevs.network.gameServer.GameServer;
import com.tanksDevs.network.gameServer.InOut.ServerIn;
import com.tanksDevs.network.gameServer.InOut.ServerOut;
import com.tanksDevs.network.gameServer.ServerSupplier;
import com.tanksDevs.network.parser.PojoParser;
import com.tanksDevs.system.entity.Colliding;
import com.tanksDevs.system.entity.forest.SimpleForest;
import com.tanksDevs.system.entity.forest.SimpleForestPojo;
import com.tanksDevs.system.entity.tank.SimpleTank;
import com.tanksDevs.system.entity.tank.Tank;
import com.tanksDevs.system.game.Game;
import com.tanksDevs.system.game.SimpleGame;
import com.tanksDevs.system.player.Player;

import java.io.IOException;
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
    private Game game;
    private final Kryo kryo;
    private ServerIn serverIn;
    private ServerOut serverOut;
    private int LONG_WAIT_LENGTH = 2000;
    private int SHORT_WAIT_LENGTH = 10;

    public static GameServer createKryoServer(ServerSupplier serverSupplier) {
        return new KryoServer(serverSupplier);
    }

    private KryoServer(ServerSupplier serverSupplier) {
        this.parser = serverSupplier.getParser();
        this.portTCP = serverSupplier.getPortTCP();
        this.portUDP = serverSupplier.getPortUDP();
        this.ipAddress = serverSupplier.getIpAddress();
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

        try {
            prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        executeGameLoop();

    }

    private synchronized void prepare() throws IOException {

        registerClasses();

        server.start();
        server.bind(portTCP, portUDP);


        serverIn = serverSupplier.createReceiver(server);
        serverOut = serverSupplier.createSender(server);

        Thread serverOutThread = new Thread(serverOut);
        serverOutThread.start();

        Thread serverInThread = new Thread(serverIn);
        serverInThread.start();

        boolean notReady = true;

        while ( notReady ) {

            if ( game != null && game.getPlayers().size() > 0 ) {  // todo size() == 2
                serverIn.stopPreparation();
                notReady = false;
            }

            serverOut.putGame(game);

            try {
                wait(LONG_WAIT_LENGTH);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Game imported = serverIn.getGame();

            if (imported != null) {
                this.game = imported;
            }

        }

    }

    private void executeGameLoop() {

        System.out.println("Server game loop!");
    }

    private void registerClasses() {

        kryo.register(SimpleForest.class);
        kryo.register(SimpleForestPojo.class);
        kryo.register(SimpleGame.class);
        kryo.register(HashSet.class);
        kryo.register(Colliding.class);
        kryo.register(Tank.class);
        kryo.register(SimpleTank.class);
        kryo.register(Player.class);
    }
}
