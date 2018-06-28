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
    private int LONG_WAIT_LENGTH = 2000;
    private int SHORT_WAIT_LENGTH = 10;

    private Game game;
    private GlobalState globalState;

    public static ServerOut getInstance(Server server, PojoParser pojoParser) {
        return new KryoServerOut(server, pojoParser);
    }

    private KryoServerOut(Server server, PojoParser pojoParser) {
        this.server = server;
        this.pojoParser = pojoParser;
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

    }

    private synchronized void prepare() {

        while (! shouldStopPreparation ) {

            if (game != null) {
                server.sendToAllTCP( pojoParser.parse(game) );
            }
            try {
                wait(LONG_WAIT_LENGTH);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
