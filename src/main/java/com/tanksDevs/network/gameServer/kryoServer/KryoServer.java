package com.tanksDevs.network.gameServer.kryoServer;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Server;
import com.tanksDevs.network.gameServer.GameServer;
import com.tanksDevs.network.gameServer.InOut.ServerIn;
import com.tanksDevs.network.gameServer.InOut.ServerOut;
import com.tanksDevs.network.gameServer.ServerSupplier;
import com.tanksDevs.network.kryoHelper.KryoRegister;
import com.tanksDevs.network.parser.PojoParser;
import com.tanksDevs.network.states.GlobalState;
import com.tanksDevs.network.states.LocalState;
import com.tanksDevs.network.states.ServerState;
import com.tanksDevs.system.game.Game;
import com.tanksDevs.system.player.Player;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class KryoServer implements GameServer {

    private final PojoParser parser;
    private final int portTCP;
    private final int portUDP;
    private final int largeTimeWindow;
    private final int shortTimeWindow;
    private final String ipAddress;
    private final Server server;
    private final ServerSupplier serverSupplier;
    private final KryoRegister kryoRegister;
    private final Set<Player> players;
    private Game game;
    private final Kryo kryo;
    private ServerIn serverIn;
    private ServerOut serverOut;

    public static GameServer createKryoServer(ServerSupplier serverSupplier) {
        return new KryoServer(serverSupplier);
    }

    private KryoServer(ServerSupplier serverSupplier) {
        this.parser = serverSupplier.getParser();
        this.portTCP = serverSupplier.getPortTCP();
        this.portUDP = serverSupplier.getPortUDP();
        this.largeTimeWindow = serverSupplier.getLargeTimeWindow();
        this.shortTimeWindow = serverSupplier.getShortTimeWindow();
        this.ipAddress = serverSupplier.getIpAddress();
        this.serverSupplier = serverSupplier;
        this.kryoRegister = serverSupplier.getKryoRegister();
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

            if ( game != null && game.getPlayers().size() == game.getTanks().size()-1) {
                serverIn.stopPreparation();
                serverOut.stopPreparation();
                notReady = false;
            }



            try {
                wait(largeTimeWindow);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Game imported = serverIn.getGame();

            System.out.println("Game imported: " + imported);

            if (imported != null) {
                this.game = imported;
            }

            serverOut.putGame(game);

        }

    }

    private synchronized void executeGameLoop() {

        System.out.println("Server game loop!");

        // temporary pseudo loop ;)


        boolean hasWinner = false;
        int counter = 0;

        while (! hasWinner ) {


            // send GlobalState

            GlobalState state = new ServerState();
            state.setId(counter);

            serverOut.putGlobalState(state);

            counter++;

            try {
                wait(shortTimeWindow);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            LocalState localState = serverIn.getLocalState();
            System.out.println("Local state, server.. " + localState);

            if (counter > 100000) {
                hasWinner = true;
            }

        }


        System.out.println("End SERVER game loop");

    }

    private void registerClasses() {
        kryoRegister.register(kryo);
    }
}
