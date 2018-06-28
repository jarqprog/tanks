package com.tanksDevs.system.board;

import com.tanksDevs.system.board.tiles.Cell;
import com.tanksDevs.system.board.tiles.Tile;
import com.tanksDevs.system.board.utils.JsonReader;

import java.util.List;

public class TileBoard implements Board {

    private int width;
    private int height;

    private Cell[][] tiles;

    public TileBoard(int width, int height) {
        this.width = width;
        this.height = height;
        createEmptyMap();
    }

    private void createEmptyMap() {
        tiles = new Tile[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tiles[x][y] = new Tile(x, y);
            }
        }
    }

    @Override
    public Cell getTile(int x, int y) {
        return tiles[x][y];
    }

    @Override
    public void setTile(int x, int y, Cell newTile) {
        tiles[x][y] = newTile;
    }

    @Override
    public void importFromJSON(String jsonString) {
        JsonReader jsonReader = new JsonReader();
        List<Tile> tiles = jsonReader.getJsonElementsAsArray(jsonString, Tile.class, width * height);
        copyTilesFromList(tiles);
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    private void copyTilesFromList(List<Tile> tiles) {
        for (Tile t : tiles) {
            setTile(t.getX(), t.getY(), t);
        }
    }

    public void printMap() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                System.out.println(String.format("%s : %d : %d", getTile(i, j).getTileType().toString(), i, j));
            }
        }
    }
}
