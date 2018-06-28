package com.tanksDevs.system.entity.forest;

import com.tanksDevs.system.entity.AbstractEntity;
import com.tanksDevs.system.entity.Genre;

public class SimpleForest extends AbstractEntity implements Forest {

    private final Genre genre = Genre.FOREST;

    public SimpleForest(int id, double x, double y, double size) {
        super(id, x, y, size);
    }

    public SimpleForest(ForestPojo forestPojo) {
        super(forestPojo.getId(), forestPojo.getX(), forestPojo.getY(), forestPojo.getSize());
    }

    @Override
    public Genre getGenre() {
        return genre;
    }
}
