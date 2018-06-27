package com.tanksDevs.network.gameServer.InOut;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Server;
import com.tanksDevs.network.states.GlobalState;
import com.tanksDevs.system.game.Game;

public class KryoServerOut implements ServerOut {


    private Game game;
    private GlobalState globalState;
    private final Server server;
    private final String ipAddress;
    private final int tcpPort;
    private final int udpPort;
    private final Kryo kryo;

    public KryoServerOut(int tcpPort, int udpPort, String ipAddress, Kryo kryo, Server server) {
        this.server = server;
        this.ipAddress = ipAddress;
        this.tcpPort = tcpPort;
        this.udpPort = udpPort;
        this.kryo = kryo;
    }

    @Override
    public void put(GlobalState globalState) {

        this.globalState = globalState;
    }

    @Override
    public void put(Game game) {

        this.game = game;

    }

    @Override
    public synchronized void run() {

//        server.addListener(new Listener() {
//            public void received (Connection connection, Object object) {
//                if (object instanceof SomeRequest) {
//                    SomeRequest request = (SomeRequest)object;
//                    System.out.println(request.text);
//
//                    SomeResponse response = new SomeResponse();
//                    response.text = "Thanks";
//                    connection.sendTCP(response);
//                }
//            }
//        });


        while (game == null) {

            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        server.sendToAllTCP(game);


        System.out.println("Server: game was sent");

    }
}
