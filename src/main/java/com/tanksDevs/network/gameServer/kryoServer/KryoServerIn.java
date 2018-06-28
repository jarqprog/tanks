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
    private final int largeTimeWindow;
    private final int shortTimeWindow;
    private boolean shouldStop;  // if true - stop thread
    private boolean shouldStopPreparation;


    private Game game;
    private LocalState localState;


    public static ServerIn getInstance(Server server, PojoParser pojoParser,
                                       int largeTimeWindow, int shortTimeWindow) {
        return new KryoServerIn(server, pojoParser, largeTimeWindow, shortTimeWindow);
    }


    private KryoServerIn(Server server, PojoParser pojoParser, int largeTimeWindow, int shortTimeWindow) {
        this.server = server;
        this.pojoParser = pojoParser;
        this.largeTimeWindow = largeTimeWindow;
        this.shortTimeWindow = shortTimeWindow;
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

            server.addListener(new Listener() {

                public void received (Connection connection, Object object) {
                if (object instanceof GamePojo) {
                    game = pojoParser.parse( (GamePojo) object);
                }
                }
            });

            try {
                wait(largeTimeWindow);
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
    }
}
