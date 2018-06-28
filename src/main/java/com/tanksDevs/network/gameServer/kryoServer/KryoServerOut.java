package com.tanksDevs.network.gameServer.kryoServer;

import com.esotericsoftware.kryonet.Server;
import com.tanksDevs.network.gameServer.InOut.ServerOut;
import com.tanksDevs.network.parser.PojoParser;
import com.tanksDevs.network.states.GlobalState;
import com.tanksDevs.system.game.Game;

public class KryoServerOut implements ServerOut {

    private final Server server;
    private final PojoParser pojoParser;

    private boolean shouldStop;  // if true - stop thread
    private boolean shouldStopPreparation;
    private final int largeTimeWindow;
    private final int shortTimeWindow;

    private Game game;
    private GlobalState globalState;

    public static ServerOut getInstance(Server server, PojoParser pojoParser,
                                        int largeTimeWindow, int shortTimeWindow) {
        return new KryoServerOut(server, pojoParser, largeTimeWindow, shortTimeWindow);
    }

    private KryoServerOut(Server server, PojoParser pojoParser, int largeTimeWindow, int shortTimeWindow) {
        this.server = server;
        this.pojoParser = pojoParser;
        this.largeTimeWindow = largeTimeWindow;
        this.shortTimeWindow = shortTimeWindow;
    }

    @Override
    public void putGlobalState(GlobalState globalState) {

        this.globalState = globalState;
    }

    @Override
    public void putGame(Game game) {
        this.game = game;

    }

    @Override
    public void stop() {
        this.shouldStop = true;
    }

    @Override
    public void stopPreparation() {
        this.shouldStopPreparation = true;
    }

    @Override
    public synchronized void run() {

        prepare();
        handleGame();

    }

    private synchronized void prepare() {

        while (! shouldStopPreparation ) {

            if (game != null) {
                server.sendToAllTCP( pojoParser.parse(game) );
            }
            try {
                wait(largeTimeWindow);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private synchronized void handleGame() {

        while (! shouldStop ) {

            if (globalState != null) {
                server.sendToAllUDP( globalState );
                globalState = null;
            }
            try {
                wait(shortTimeWindow);  // maybe notify all?
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
