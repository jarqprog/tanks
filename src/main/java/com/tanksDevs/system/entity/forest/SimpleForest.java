package com.tanksDevs.system.entity.forest;

import com.tanksDevs.system.entity.AbstractEntity;
import com.tanksDevs.system.entity.Genre;

public class SimpleForest extends AbstractEntity implements Forest {

    private final Genre genre;

    public SimpleForest(int id, double x, double y, double size) {
        super(id, x, y, size);
        this.genre = Genre.FOREST;
    }

    @Override
    public Genre getGenre() {
        return genre;
    }
}
