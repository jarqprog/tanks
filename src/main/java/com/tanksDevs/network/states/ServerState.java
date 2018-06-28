package com.tanksDevs.network.states;

public class ServerState implements GlobalState {

    private int id;  // temporary!!!

    @Override
    public GlobalState getGlobalState() {
        return new ServerState();
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }


    @Override
    public String toString() {
        return "ServerState{} " + id;
    }
}
