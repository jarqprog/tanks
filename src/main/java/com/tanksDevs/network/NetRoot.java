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
import com.tanksDevs.system.entity.tank.SimpleTank;
import com.tanksDevs.system.entity.tank.Tank;
import com.tanksDevs.system.game.Game;
import com.tanksDevs.system.game.SimpleGame;
import com.tanksDevs.system.player.Player;
import com.tanksDevs.system.player.User;

import java.util.HashSet;
import java.util.Set;

public class NetRoot implements Root {


    public static Root createNetRoot() {
        return new NetRoot();
    }


    private NetRoot() {}

    @Override
    public void start() {

        // scenario: running server as client's thread

        int portTCP = 9999;
        int portUDP = 9900;
        int largeTimeWindow = 500;
        int shortTimeWindow = 5;
        String ipAddress = "127.0.0.1";

        PojoParser parser = new NaivePojoParser();
        KryoRegister kryoRegister = SimpleKryoRegister.create();
        Player player = new User("Adam");

        Set<Colliding> collidings = new HashSet<>();
        Set<Tank> tanks = new HashSet<>();

        Tank first = new SimpleTank(10, 10, 10, 10);
        Tank second = new SimpleTank(20, 20, 20, 20);

        collidings.add(first);
        tanks.add(first);
        collidings.add(second);
        tanks.add(second);


        Game game = new SimpleGame(collidings, tanks);

        ServerSupplier serverSupplier = KryoServerSupplier
                .create(portTCP, portUDP, largeTimeWindow, shortTimeWindow, ipAddress, parser, kryoRegister);
        serverSupplier.setGame(game);

        GameServer gameServer = KryoServer.createKryoServer(serverSupplier);

        ClientSupplier clientSupplier = KryoClientSupplier
                .create(portTCP, portUDP, largeTimeWindow, shortTimeWindow, ipAddress, parser,
                kryoRegister, player);

        clientSupplier.registerServer(gameServer);

        GameClient gameClient = KryoClient.createKryoClient(clientSupplier);

        gameClient.runClient();
    }

}
