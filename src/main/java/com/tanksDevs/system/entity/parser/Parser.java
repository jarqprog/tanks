package com.tanksDevs.system.entity.parser;

import com.tanksDevs.system.board.Board;
import com.tanksDevs.system.entity.Direction;
import com.tanksDevs.system.entity.Entity;
import com.tanksDevs.system.entity.eagle.Eagle;
import com.tanksDevs.system.entity.eagle.EaglePojo;
import com.tanksDevs.system.entity.forest.SimpleForest;
import com.tanksDevs.system.entity.obstacle.BrickWall;
import com.tanksDevs.system.entity.obstacle.SteelWall;
import com.tanksDevs.system.entity.tank.SimpleTank;
import com.tanksDevs.system.entity.tank.TankPojo;
import com.tanksDevs.system.entity.water.SimpleWater;
import com.tanksDevs.system.pojo.EntityPojo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Parser implements DataParser {

    private Board board;

    public Parser(Board board) {
        this.board = board;
    }

    @Override
    public Set<EntityPojo> parseMapToPojo() {
        Set<EntityPojo> entities = new HashSet<>();

        for (int x = 0; x < board.getHeight(); x++) {
            for (int y = 0; y < board.getWidth(); y++) {
                EntityPojo entity = board.getTile(x, y).parseToEntityPojo();
                if (entity != null) {
                    entities.add(entity);
                }
            }
        }
        return entities;
    }

    @Override
    public Set<Entity> parsePojosToEntities(Set<EntityPojo> pojos) {
        Set<Entity> entities = new HashSet<>();
        HashMap<Integer, TankPojo> tanks = new HashMap<>();
        HashMap<Integer, EaglePojo> eagles = new HashMap<>();

        for (EntityPojo pojo : pojos) {
            Entity entity = parsePojoToEntity(pojo, tanks, eagles);
            if (entity != null) {
                entities.add(entity);
            }
        }
        createTanksWithBases(entities, tanks, eagles);
        return entities;
    }

    private Entity parsePojoToEntity(EntityPojo pojo, HashMap<Integer, TankPojo> tanks, HashMap<Integer, EaglePojo> eagles) {
        Entity entity = null;

        switch (pojo.getGenre()) {
            case BRICKWALL:
                entity = new BrickWall(pojo.getId(), pojo.getX(), pojo.getY(), 1);
                break;
            case STEELWALL:
                entity = new SteelWall(pojo.getId(), pojo.getX(), pojo.getY(), 1);
                break;
            case TANK:
                TankPojo tank = (TankPojo) pojo;
                tanks.put(tank.getPlayerOwner(), tank);
                break;
            case FOREST:
                entity = new SimpleForest(pojo.getId(), pojo.getX(), pojo.getY(), 1);
                break;
            case WATER:
                entity = new SimpleWater(pojo.getId(), pojo.getX(), pojo.getY(), 1);
                break;
            case EAGLE:
                EaglePojo eagle = (EaglePojo) pojo;
                eagles.put(eagle.getPlayerOwner(), eagle);
                break;
        }
        return entity;
    }

    private void createTanksWithBases(Set<Entity> entities, HashMap<Integer, TankPojo> tanks, HashMap<Integer, EaglePojo> eagles) {
        for (Integer key : tanks.keySet()) {
            EaglePojo eaglePojo = eagles.get(key);
            TankPojo tankPojo = tanks.get(key);

            Eagle eagle = new Eagle(eaglePojo.getId(), eaglePojo.getX(), eaglePojo.getY(), eaglePojo.getSize());
            Entity tank = new SimpleTank(tankPojo.getId(), tankPojo.getX(), tankPojo.getY(), tankPojo.getSize(), Direction.NORTH, eagle);

            entities.add(eagle);
            entities.add(tank);
        }
    }
}
