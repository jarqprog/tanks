package com.tanksDevs.network.gameClient.kryoClient;

import com.esotericsoftware.kryonet.Client;
import com.tanksDevs.network.gameClient.InOut.ClientOut;
import com.tanksDevs.network.parser.PojoParser;
import com.tanksDevs.network.states.LocalState;
import com.tanksDevs.system.game.Game;

public class KryoClientOut implements ClientOut {


    private final Client client;
    private final PojoParser pojoParser;
    private final int largeTimeWindow;
    private final int shortTimeWindow;



    private boolean shouldStop;  // if true - stop thread
    private boolean shouldStopPreparation;


    private Game game;
    private LocalState localState;


    public static ClientOut getInstance(Client client, PojoParser pojoParser, int largeTimeWindow, int shortTimeWindow) {
        return new KryoClientOut(client, pojoParser, largeTimeWindow, shortTimeWindow);
    }


    private KryoClientOut(Client client, PojoParser pojoParser, int largeTimeWindow, int shortTimeWindow) {
        this.client = client;
        this.pojoParser = pojoParser;
        this.largeTimeWindow = largeTimeWindow;
        this.shortTimeWindow = shortTimeWindow;
    }


    @Override
    public void run() {

        prepare();
        handleGame();

        client.close();

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
                wait(largeTimeWindow);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private synchronized void handleGame() {

        while (! shouldStop ) {

            if (localState != null) {
                client.sendUDP( localState );
                localState = null;
            }

            try {
                wait(shortTimeWindow);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
