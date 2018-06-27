package com.tanksDevs.system.board.tiles;


public class Tile implements Cell{

    private Type tileType;

    private int x;
    private int y;

    public Tile(int x, int y) {
        tileType = Type.EMPTY;
        this.x = x;
        this.y = y;
    }

    @Override
    public Type getTileType() {
        return tileType;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }
}
