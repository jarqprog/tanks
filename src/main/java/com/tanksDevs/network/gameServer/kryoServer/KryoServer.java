package com.tanksDevs.network.gameServer.kryoServer;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Server;
import com.tanksDevs.network.gameServer.GameServer;
import com.tanksDevs.network.gameServer.InOut.ServerIn;
import com.tanksDevs.network.gameServer.InOut.ServerOut;
import com.tanksDevs.network.gameServer.ServerSupplier;
import com.tanksDevs.network.input.UserInput;
import com.tanksDevs.network.kryoHelper.KryoRegister;
import com.tanksDevs.network.parser.PojoParser;
import com.tanksDevs.network.states.GlobalState;
import com.tanksDevs.network.states.LocalState;
import com.tanksDevs.network.states.ServerState;
import com.tanksDevs.system.entity.Direction;
import com.tanksDevs.system.entity.tank.Tank;
import com.tanksDevs.system.game.Game;
import com.tanksDevs.system.player.Player;

import java.io.IOException;
import java.util.*;

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

    private Map<Integer, Tank> tanks;

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
        this.tanks = new HashMap<>();

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

            if ( game != null && game.getPlayers().size() == game.getTanks().size() ) {
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

            Iterator<Tank> tanksIterator = game.getTanks().iterator();
            while (tanksIterator.hasNext()) {
                Tank tank = tanksIterator.next();
                tanks.put(tank.getId(), tank);
            }
        }

    }

    private synchronized void executeGameLoop() {

        System.out.println("Server game loop!");

        // temporary pseudo loop ;)


        boolean hasWinner = false;
        int counter = 0;

        long lastTime = System.nanoTime();
        double tickRate = 60.0;
        double ns = 1000000000 / tickRate;
        double delta = 0;

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

            // gameplay loop

            System.out.println("========== tank 1 coordinate test ============");
            System.out.println(game.getTanks().iterator().next().getX());
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while(delta >= 1) {
                handleInputs(localState);
                update();
                delta -= 1;
            }
        }


        System.out.println("End SERVER game loop");

    }

    private void registerClasses() {
        kryoRegister.register(kryo);
    }

    private void handleInputs(LocalState localState) {

        Tank tank = tanks.get(localState.getTankId());

        switch(localState.getUserInput()) {
            case UP:
                tank.move(Direction.NORTH);
                break;
            case DOWN:
                tank.move(Direction.SOUTH);
                break;
            case LEFT:
                tank.move(Direction.WEST);
                break;
            case RIGHT:
                tank.move(Direction.EAST);
                break;
            case SHOOT:
                // TODO shoot
                break;
            case NOTHING:
                // TODO ???
                break;
        }
    }

    private void update() {
        for (Tank tank : game.getTanks()) {
            tank.move(tank.getDirection());
        }
    }
}
