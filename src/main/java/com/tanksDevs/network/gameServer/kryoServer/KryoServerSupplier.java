package com.tanksDevs.network.gameServer.kryoServer;

import com.esotericsoftware.kryonet.Server;
import com.tanksDevs.network.gameServer.InOut.ServerIn;
import com.tanksDevs.network.gameServer.InOut.ServerOut;
import com.tanksDevs.network.gameServer.ServerSupplier;
import com.tanksDevs.network.parser.PojoParser;
import com.tanksDevs.system.board.Board;
import com.tanksDevs.system.game.Game;


public class KryoServerSupplier implements ServerSupplier {

    private final int portTCP;
    private final int portUDP;
    private final String ipAddress;

    private final PojoParser pojoParser;
    private Game game;
    private Board board;

    public KryoServerSupplier(int portTCP, int portUDP, String ipAddress, PojoParser pojoParser) {
        this.portTCP = portTCP;
        this.portUDP = portUDP;
        this.ipAddress = ipAddress;
        this.pojoParser = pojoParser;
    }

    @Override
    public ServerIn createReceiver(Server server) {
        return KryoServerIn.getInstance(server, pojoParser);
    }

    @Override
    public ServerOut createSender(Server server) {
        return KryoServerOut.getInstance(server, pojoParser);
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
}
