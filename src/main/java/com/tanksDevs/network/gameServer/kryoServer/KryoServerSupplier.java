package com.tanksDevs.network.gameServer.kryoServer;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Server;
import com.tanksDevs.network.gameServer.InOut.KryoServerOut;
import com.tanksDevs.network.gameServer.InOut.ServerIn;
import com.tanksDevs.network.gameServer.InOut.ServerOut;
import com.tanksDevs.network.gameServer.ServerSupplier;
import com.tanksDevs.network.parser.PojoParser;
import com.tanksDevs.system.board.Board;
import com.tanksDevs.system.game.Game;



public class KryoServerSupplier implements ServerSupplier {



    @Override
    public ServerIn createReciever(int tcpPort, int udpPort, String ipAddress, Kryo kryo, Server server) {
        return null;
    }

    @Override
    public ServerOut createSender(int tcpPort, int udpPort, String ipAddress, Kryo kryo, Server server) {
        return new KryoServerOut(tcpPort, udpPort, ipAddress, kryo, server);
    }

    @Override
    public int getPortTCP() {
        return 0;
    }

    @Override
    public int getPortUDP() {
        return 0;
    }

    @Override
    public Board getBoard() {
        return null;
    }

    @Override
    public Game getGame() {
        return null;
    }

    @Override
    public PojoParser getParser() {
        return null;
    }
}
