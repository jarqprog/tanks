package com.tanksDevs.network.gameServer.kryoServer;

import com.esotericsoftware.kryonet.Server;
import com.tanksDevs.network.gameServer.InOut.ServerIn;
import com.tanksDevs.network.gameServer.InOut.ServerOut;
import com.tanksDevs.network.gameServer.ServerSupplier;
import com.tanksDevs.network.kryoHelper.KryoRegister;
import com.tanksDevs.network.parser.PojoParser;
import com.tanksDevs.system.board.Board;
import com.tanksDevs.system.game.Game;


public class KryoServerSupplier implements ServerSupplier {

    private final int portTCP;
    private final int portUDP;
    private final int largeTimeWindow;
    private final int shortTimeWindow;
    private final String ipAddress;

    private final PojoParser pojoParser;
    private final KryoRegister kryoRegister;
    private Game game;
    private Board board;

    public static ServerSupplier create(int portTCP, int portUDP,
                                        int largeTimeWindow, int shortTimeWindow,
                                        String ipAddress, PojoParser pojoParser,
                                        KryoRegister kryoRegister) {
        return new KryoServerSupplier(portTCP, portUDP, largeTimeWindow, shortTimeWindow,
                ipAddress, pojoParser, kryoRegister);
    }

    private KryoServerSupplier(int portTCP, int portUDP,
                               int largeTimeWindow, int shortTimeWindow,
                               String ipAddress, PojoParser pojoParser,
                               KryoRegister kryoRegister) {
        this.portTCP = portTCP;
        this.portUDP = portUDP;
        this.largeTimeWindow = largeTimeWindow;
        this.shortTimeWindow = shortTimeWindow;
        this.ipAddress = ipAddress;
        this.pojoParser = pojoParser;
        this.kryoRegister = kryoRegister;
    }

    @Override
    public ServerIn createReceiver(Server server) {
        return KryoServerIn.getInstance(server, pojoParser, largeTimeWindow, shortTimeWindow);
    }

    @Override
    public ServerOut createSender(Server server) {
        return KryoServerOut.getInstance(server, pojoParser, largeTimeWindow, shortTimeWindow);
    }

    @Override
    public int getPortTCP() {
        return portTCP;
    }

    @Override
    public int getPortUDP() {
        return portUDP;
    }

    @Override
    public String getIpAddress() {
        return ipAddress;
    }

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public Game getGame() {
        return game;
    }

    @Override
    public PojoParser getParser() {
        return pojoParser;
    }

    @Override
    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public void setBoard(Board board) {
        this.board = board;
    }

    @Override
    public KryoRegister getKryoRegister() {
        return kryoRegister;
    }

    @Override
    public int getLargeTimeWindow() {
        return largeTimeWindow;
    }

    @Override
    public int getShortTimeWindow() {
        return shortTimeWindow;
    }

}
