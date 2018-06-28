package com.tanksDevs.network.gameServer.kryoServer;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.tanksDevs.network.gameServer.InOut.ServerIn;
import com.tanksDevs.network.parser.PojoParser;
import com.tanksDevs.network.states.LocalState;
import com.tanksDevs.system.game.Game;
import com.tanksDevs.system.game.GamePojo;

public class KryoServerIn implements ServerIn {


    private final Server server;
    private final PojoParser pojoParser;
    private boolean shouldStop;  // if true - stop thread
    private boolean shouldStopPreparation;
    private int LONG_WAIT_LENGTH = 1000;
    private int SHORT_WAIT_LENGTH = 10;

    private Game game;
    private LocalState localState;


    public static ServerIn getInstance(Server server, PojoParser pojoParser) {
        return new KryoServerIn(server, pojoParser);
    }


    private KryoServerIn(Server server, PojoParser pojoParser) {
        this.server = server;
        this.pojoParser = pojoParser;
    }

    @Override
    public LocalState getLocalState() {
        return localState;
    }

    @Override
    public Game getGame() {
        return this.game;
    }

    @Override
    public void run() {

        prepare();

    }

    @Override
    public void stop() {
        this.shouldStop = true;
    }

    @Override
    public void stopPreparation() {
        this.shouldStopPreparation = true;
    }


    private synchronized void prepare() {


        while (! shouldStopPreparation ) {
            try {
            System.out.println("deser!! serverIn");
            server.addListener(new Listener() {

                public void received (Connection connection, Object object) {
                    if (object instanceof GamePojo) {
                        game = pojoParser.parse( (GamePojo) object);
                    }
                }
            });


                wait(LONG_WAIT_LENGTH);
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
    }
}
