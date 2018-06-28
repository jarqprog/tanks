package com.tanksDevs.network.gameClient.kryoClient;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.tanksDevs.network.gameClient.ClientSupplier;
import com.tanksDevs.network.gameClient.GameClient;
import com.tanksDevs.network.gameServer.GameServer;
import com.tanksDevs.network.gameClient.InOut.ClientIn;
import com.tanksDevs.network.gameClient.InOut.ClientOut;
import com.tanksDevs.network.parser.NaivePojoParser;
import com.tanksDevs.network.parser.PojoParser;
import com.tanksDevs.system.entity.Colliding;
import com.tanksDevs.system.entity.Genre;
import com.tanksDevs.system.entity.bullet.SimpleBullet;
import com.tanksDevs.system.entity.forest.SimpleForest;
import com.tanksDevs.system.entity.forest.SimpleForestPojo;
import com.tanksDevs.system.entity.hitBox.BasicHitBox;
import com.tanksDevs.system.entity.hitBox.HitBox;
import com.tanksDevs.system.entity.tank.SimpleTank;
import com.tanksDevs.system.entity.tank.SimpleTankPojo;
import com.tanksDevs.system.entity.tank.Tank;
import com.tanksDevs.system.game.Game;
import com.tanksDevs.system.game.SimpleGame;
import com.tanksDevs.system.game.SimpleGamePojo;
import com.tanksDevs.system.player.Player;
import com.tanksDevs.system.player.UserPojo;

import java.io.IOException;
import java.util.HashSet;
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
    private final Client client;
    private final Kryo kryo;

    private ClientIn clientIn;
    private ClientOut clientOut;
    private Game game;


    public static GameClient createKryoClient(ClientSupplier clientSupplier) {
        return new KryoClient(clientSupplier);
    }

    private KryoClient(ClientSupplier clientSupplier) {
        this.parser = clientSupplier.getParser();
        this.portTCP = clientSupplier.getPortTCP();
        this.portUDP = clientSupplier.getPortUDP();
        this.ipAddress = clientSupplier.getIpAddress();
        this.clientSupplier = clientSupplier;
        this.player = clientSupplier.getPlayer();
        this.server = clientSupplier.getGameServer();
        this.client = new Client();
        this.kryo = client.getKryo();
    }

    @Override
    public void runClient() {

        try {
            prepareGame();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            System.out.println("Problem occurred, exiting program!");
        }

        executeGameLoop();
    }


    private synchronized void prepareGame() throws IOException, InterruptedException {

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
            imported = clientIn.getGame();


            wait(200);

        }

        this.game = imported;

        System.out.println("Client, I've game! " + game);

        game.registerPlayer(player);
        Tank myTank;

        // todo ;0)
        for (Iterator<Tank> it = game.getTanks().iterator(); it.hasNext(); ) {
            myTank = it.next();
            if (! myTank.hasPlayer() ) {
                myTank.markAsOccupied();
                player.setTank(myTank);
                clientIn.stopPreparation();
                break;
            }
        }

        System.out.println("Player registered, tank is chosen");




    }


    private void setupInOut() throws IOException {

        int TIMEOUT = 5000;
        client.start();
        client.connect(TIMEOUT, ipAddress, portTCP, portUDP);

        System.out.println("Client connected!");

        clientIn = clientSupplier.createReceiver(client);
        clientOut = clientSupplier.createSender(client);

    }

    private void executeGameLoop() {

        System.out.println("Client game loop");

    }

    private void registerClasses() {
        kryo.register(SimpleGamePojo.class);


        kryo.register(Genre.class);

        kryo.register(SimpleGamePojo.class);
        kryo.register(SimpleTankPojo.class);


        kryo.register(SimpleForest.class);
        kryo.register(SimpleForestPojo.class);
        kryo.register(SimpleGame.class);
        kryo.register(HashSet.class);

        kryo.register(Tank.class);
        kryo.register(SimpleTank.class);
        kryo.register(SimpleTankPojo.class);

        kryo.register(Player.class);
        kryo.register(UserPojo.class);

        kryo.register(BasicHitBox.class);
        kryo.register(HitBox.class);

        kryo.register(Colliding.class);


        kryo.register(SimpleBullet.class);

    }

}
