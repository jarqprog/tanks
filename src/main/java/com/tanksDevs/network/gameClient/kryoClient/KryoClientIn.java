package com.tanksDevs.network.gameClient.kryoClient;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.tanksDevs.network.gameClient.InOut.ClientIn;
import com.tanksDevs.network.parser.PojoParser;
import com.tanksDevs.network.states.GlobalState;
import com.tanksDevs.system.game.Game;
import com.tanksDevs.system.game.GamePojo;

public class KryoClientIn implements ClientIn {

    private final Client client;
    private final PojoParser pojoParser;

    private Game game;
    private GlobalState globalState;
    private boolean shouldStop;  // if true - stop thread
    private boolean shouldStopPreparation;
    private int LONG_WAIT_LENGTH = 1000;
    private int SHORT_WAIT_LENGTH = 10;


    public static ClientIn getInstance(Client client, PojoParser pojoParser) {
        return new KryoClientIn(client, pojoParser);
    }



    private KryoClientIn(Client client, PojoParser pojoParser) {
        this.client = client;
        this.pojoParser = pojoParser;
    }

    @Override
    public GlobalState getGlobalState() {

        GlobalState toReturn = globalState;
        globalState = null;
        return toReturn;
    }

    @Override
    public Game getGame() {

        Game toReturn = game;
        game = null;
        return toReturn;
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
            try {
            if (game == null) {
                System.out.println("deser!! clientIn");
                client.addListener(new Listener() {

                    public void received(Connection connection, Object object) {
                        if (object instanceof GamePojo) {

                            game = pojoParser.parse( (GamePojo) object );
                        }
                    }
                });
            }


                wait(LONG_WAIT_LENGTH);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }
}
