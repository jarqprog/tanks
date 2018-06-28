package com.tanksDevs.system.board;

import com.tanksDevs.system.board.tiles.Cell;

public interface Board {
    Cell getTile(int x, int y);

    void setTile(int x, int y, Cell cell);

    void importFromJSON(String jsonString);
}
