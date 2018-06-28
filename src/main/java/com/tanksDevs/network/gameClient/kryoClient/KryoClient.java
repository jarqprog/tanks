package com.tanksDevs.network.gameClient.kryoClient;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.tanksDevs.network.gameClient.ClientSupplier;
import com.tanksDevs.network.gameClient.GameClient;
import com.tanksDevs.network.gameServer.GameServer;
import com.tanksDevs.network.gameClient.InOut.ClientIn;
import com.tanksDevs.network.gameClient.InOut.ClientOut;
import com.tanksDevs.network.input.UserInput;
import com.tanksDevs.network.kryoHelper.KryoRegister;
import com.tanksDevs.network.parser.PojoParser;
import com.tanksDevs.network.states.GlobalState;
import com.tanksDevs.network.states.LocalState;
import com.tanksDevs.network.states.TankState;
import com.tanksDevs.system.entity.tank.Tank;
import com.tanksDevs.system.game.Game;
import com.tanksDevs.system.player.Player;

import java.io.IOException;
import java.util.Iterator;

public class KryoClient implements GameClient {

    // use reference to java fx

    private final int portTCP;
    private final int portUDP;
    private final String ipAddress;
    private final ClientSupplier clientSupplier;
    private final GameServer server;
    private final Player player;
    private final PojoParser parser;
    private final KryoRegister kryoRegister;
    private final Client client;
    private final Kryo kryo;

    private final int largeTimeWindow;
    private final int shortTimeWindow;

    private ClientIn clientIn;
    private ClientOut clientOut;
    private Game game;
    private Tank tank;


    public static GameClient createKryoClient(ClientSupplier clientSupplier) {
        return new KryoClient(clientSupplier);
    }

    private KryoClient(ClientSupplier clientSupplier) {
        this.parser = clientSupplier.getParser();
        this.portTCP = clientSupplier.getPortTCP();
        this.portUDP = clientSupplier.getPortUDP();
        this.largeTimeWindow = clientSupplier.getLargeTimeWindow();
        this.shortTimeWindow = clientSupplier.getShortTimeWindow();
        this.ipAddress = clientSupplier.getIpAddress();
        this.clientSupplier = clientSupplier;
        this.kryoRegister = clientSupplier.getKryoRegister();
        this.player = clientSupplier.getPlayer();
        this.server = clientSupplier.getGameServer();
        this.client = new Client();
        this.kryo = client.getKryo();

    }

    @Override
    public void runClient() {

        try {
            prepareGame();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (tank != null) {
            executeGameLoop();
        }
    }


    private synchronized void prepareGame() throws InterruptedException {

        registerClasses();

        if (clientSupplier.hasServer() ) {

            // run this server!
            Thread serverThread = new Thread(server);
            serverThread.start();
        }

        setupInOut();


        Thread clientInThread = new Thread(clientIn);

        clientInThread.start();

        Thread clientOutThread = new Thread(clientOut);

        clientOutThread.start();

        Game imported = null;

        while ( imported == null ) {
            this.clientOut.putGame(game);

            wait(largeTimeWindow);

            imported = clientIn.getGame();
        }

        this.game = imported;

        Tank myTank;

        // todo ;0)
        for (Iterator<Tank> it = game.getTanks().iterator(); it.hasNext(); ) {
            myTank = it.next();
            if (! myTank.hasPlayer() ) {

                myTank.markAsOccupied();
                player.setTank(myTank);
                tank = myTank;
                clientIn.stopPreparation();
                game.registerPlayer(player);
                clientOut.putGame(game);
                break;
            }
        }

        System.out.println("Player registered, tank is chosen");
    }

    private void setupInOut() {


        int TIMEOUT = 10000;
        client.start();

        boolean notConnected = true;
        while (notConnected) {

            try {
                wait(largeTimeWindow);
                client.connect(TIMEOUT, ipAddress, portTCP, portUDP);
                notConnected = false;
            } catch (IOException | InterruptedException notUsed) {
                System.out.println("Connection failure");
            }
        }

        System.out.println("Client connected!");

        clientIn = clientSupplier.createReceiver(client);
        clientOut = clientSupplier.createSender(client);

    }

    private synchronized void executeGameLoop() {

        System.out.println("Client game loop");

        // temporary pseudo loop ;)


        boolean hasWinner = false;
        int counter = 0;

        while (! hasWinner ) {


            LocalState localState = new TankState();
            localState.setTankId(tank.getId());

            if (counter % 2 == 0) {
                localState.setUserInput(UserInput.DOWN);
            } else {
                localState.setUserInput(UserInput.UP);
            }

            clientOut.putLocalState(localState);

            counter++;

            try {
                wait(shortTimeWindow);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // send GlobalState
            GlobalState globalState = clientIn.getGlobalState();
            System.out.println("Global state, client.. " + globalState);

            if (counter > 100000) {
                hasWinner = true;
            }

        }

        System.out.println("End CLIENT game loop");
    }

    private void registerClasses() {
        kryoRegister.register(kryo);
    }

}
