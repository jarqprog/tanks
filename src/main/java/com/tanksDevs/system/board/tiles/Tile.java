package com.tanksDevs.system.board.tiles;


import com.tanksDevs.system.entity.Genre;
import com.tanksDevs.system.entity.eagle.EaglePojo;
import com.tanksDevs.system.entity.forest.SimpleForestPojo;
import com.tanksDevs.system.entity.obstacle.BrickWallPojo;
import com.tanksDevs.system.entity.obstacle.SteelWallPojo;
import com.tanksDevs.system.entity.tank.SimpleTankPojo;
import com.tanksDevs.system.entity.water.SimpleWaterPojo;
import com.tanksDevs.system.idgenerator.BasicIDGenerator;
import com.tanksDevs.system.pojo.EntityPojo;

public class Tile implements Cell {

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

    @Override
    public EntityPojo parseToEntityPojo() {
        EntityPojo entity = null;
        int id = BasicIDGenerator.getNewID();
        switch (tileType) {
            case WALL:
                entity = new BrickWallPojo();
                break;
            case WATER:
                entity = new SimpleWaterPojo();
                break;
            case SOLID_WALL:
                entity = new SteelWallPojo();
                break;
            case FOREST:
                entity = new SimpleForestPojo();
                break;
            case EAGLE_ONE:
                entity = new EaglePojo();
                ((EaglePojo) entity).setPlayerOwner(1);
                break;
            case EAGLE_TWO:
                entity = new EaglePojo();
                ((EaglePojo) entity).setPlayerOwner(2);
                break;
            case TANK_ONE_SPAWN:
                entity = new SimpleTankPojo();
                ((SimpleTankPojo) entity).setPlayerOwner(1);
                break;
            case TANK_TWO_SPAWN:
                entity = new SimpleTankPojo();
                ((SimpleTankPojo) entity).setPlayerOwner(2);
                break;
            default:
        }
        if (entity != null) {
            entity.setX(x);
            entity.setY(y);
            entity.setGenre(parseTypeToGenre(tileType));
            entity.setId(id);
            entity.setSize(10);
        }
        return entity;
    }

    private Genre parseTypeToGenre(Type type) {
        Genre genre = null;
        switch (type) {
            case WALL:
                genre = Genre.BRICKWALL;
                break;
            case WATER:
                genre = Genre.WATER;
                break;
            case SOLID_WALL:
                genre = Genre.STEELWALL;
                break;
            case FOREST:
                genre = Genre.FOREST;
                break;
            case EAGLE_ONE:
                genre = Genre.EAGLE;
                break;
            case EAGLE_TWO:
                genre = Genre.EAGLE;
                break;
            case TANK_ONE_SPAWN:
                genre = Genre.TANK;
                break;
            case TANK_TWO_SPAWN:
                genre = Genre.TANK;
                break;
        }
        return genre;
    }
}
