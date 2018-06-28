package com.tanksDevs.network.gameClient.kryoClient;

import com.esotericsoftware.kryonet.Client;
import com.tanksDevs.network.gameClient.InOut.ClientOut;
import com.tanksDevs.network.parser.PojoParser;
import com.tanksDevs.network.states.LocalState;
import com.tanksDevs.system.game.Game;

public class KryoClientOut implements ClientOut {


    private final Client client;
    private final PojoParser pojoParser;

    private Game game;
    private LocalState localState;
    private int LONG_WAIT_LENGTH = 2000;
    private int SHORT_WAIT_LENGTH = 10;
    private boolean shouldStop;  // if true - stop thread
    private boolean shouldStopPreparation;


    public static ClientOut getInstance(Client client, PojoParser pojoParser) {
        return new KryoClientOut(client, pojoParser);
    }


    private KryoClientOut(Client client, PojoParser pojoParser) {
        this.client = client;
        this.pojoParser = pojoParser;
    }


    @Override
    public void run() {

        prepare();

    }

    @Override
    public void putLocalState(LocalState localState) {
        this.localState = localState;
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

    private synchronized void prepare() {

        while (! shouldStopPreparation ) {

            if (game != null) {
                client.sendTCP( pojoParser.parse(game) );
                game = null;
                shouldStopPreparation = true;
            }

            try {
                wait(LONG_WAIT_LENGTH);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
