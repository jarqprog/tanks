package com.tanksDevs.network;

import com.tanksDevs.Root;
import com.tanksDevs.network.gameClient.ClientSupplier;
import com.tanksDevs.network.gameClient.GameClient;
import com.tanksDevs.network.gameClient.kryoClient.KryoClient;
import com.tanksDevs.network.gameClient.kryoClient.KryoClientSupplier;
import com.tanksDevs.network.gameServer.GameServer;
import com.tanksDevs.network.gameServer.ServerSupplier;
import com.tanksDevs.network.gameServer.kryoServer.KryoServer;
import com.tanksDevs.network.gameServer.kryoServer.KryoServerSupplier;
import com.tanksDevs.network.kryoHelper.KryoRegister;
import com.tanksDevs.network.kryoHelper.SimpleKryoRegister;
import com.tanksDevs.network.parser.NaivePojoParser;
import com.tanksDevs.network.parser.PojoParser;
import com.tanksDevs.system.entity.Colliding;
import com.tanksDevs.system.entity.Direction;
import com.tanksDevs.system.entity.eagle.Eagle;
import com.tanksDevs.system.entity.tank.SimpleTank;
import com.tanksDevs.system.entity.tank.Tank;
import com.tanksDevs.system.game.Game;
import com.tanksDevs.system.game.SimpleGame;
import com.tanksDevs.system.player.Player;
import com.tanksDevs.system.player.User;
import javafx.stage.Stage;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class NetRoot implements Root {


    public static Root createNetRoot() {
        return new NetRoot();
    }


    private NetRoot() {}

    @Override
    public void start(Stage stage) {

        // scenario: running server as client's thread

        int portTCP = 9999;
        int portUDP = 9900;
        int largeTimeWindow = 500;
        int shortTimeWindow = 12;
        String ipAddress = "192.168.11.70"; // Adam
//        String ipAddress = "192.168.10.244"; // Jarek
//        String ipAddress = "192.168.10.242"; // Michal

        String userChoice = "";

        Scanner sc = new Scanner(System.in);

        while ( userChoice.length() == 0 ) {
            System.out.println("To create game, type 'C', or anything else to join existing game");

            userChoice = sc.nextLine();

        }

        // create game

        Set<Colliding> collidings = new HashSet<>();
        Set<Tank> tanks = new HashSet<>();

        Tank first = new SimpleTank(10, 10, 10, 10, Direction.NORTH,
                new Eagle(51, 51, 51, 12));
        Tank second = new SimpleTank(20, 20, 20, 20, Direction.SOUTH,
                new Eagle(52, 61, 61, 12));

        collidings.add(first);
        tanks.add(first);
        collidings.add(second);
        tanks.add(second);

        PojoParser parser = new NaivePojoParser();
        KryoRegister kryoRegister = SimpleKryoRegister.create();

        Game game = new SimpleGame(collidings, tanks);

        Player player;
        ClientSupplier clientSupplier;

        if (userChoice.toUpperCase().equals("C")) {

            player = new User("Adam");

            ServerSupplier serverSupplier = KryoServerSupplier
                    .create(portTCP, portUDP, largeTimeWindow, shortTimeWindow, ipAddress, parser, kryoRegister);
            serverSupplier.setGame(game);

            GameServer gameServer = KryoServer.createKryoServer(serverSupplier);

            clientSupplier = KryoClientSupplier
                    .create(portTCP, portUDP, largeTimeWindow, shortTimeWindow, ipAddress, parser,
                            kryoRegister, player);

            clientSupplier.registerServer(gameServer);

        } else {

            player = new User("Marcin");

            clientSupplier = KryoClientSupplier
                    .create(portTCP, portUDP, largeTimeWindow, shortTimeWindow, ipAddress, parser,
                            kryoRegister, player);

        }


        GameClient gameClient = KryoClient.createKryoClient(clientSupplier, stage);

        gameClient.runClient();
    }

}
