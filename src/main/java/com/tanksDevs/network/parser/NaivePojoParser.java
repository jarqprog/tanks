package com.tanksDevs.network.parser;

import com.tanksDevs.system.entity.Entity;
import com.tanksDevs.system.entity.Genre;

import com.tanksDevs.system.entity.eagle.Eagle;
import com.tanksDevs.system.entity.eagle.EaglePojo;
import com.tanksDevs.system.entity.eagle.TankBase;
import com.tanksDevs.system.entity.eagle.TankBasePojo;
import com.tanksDevs.system.entity.forest.ForestPojo;

import com.tanksDevs.system.entity.forest.SimpleForest;
import com.tanksDevs.system.entity.forest.SimpleForestPojo;
import com.tanksDevs.system.entity.obstacle.*;
import com.tanksDevs.system.entity.tank.SimpleTank;
import com.tanksDevs.system.entity.tank.SimpleTankPojo;
import com.tanksDevs.system.entity.tank.Tank;
import com.tanksDevs.system.entity.tank.TankPojo;
import com.tanksDevs.system.entity.water.SimpleWater;
import com.tanksDevs.system.entity.water.SimpleWaterPojo;
import com.tanksDevs.system.entity.water.WaterPojo;
import com.tanksDevs.system.game.Game;
import com.tanksDevs.system.game.GamePojo;
import com.tanksDevs.system.player.Player;
import com.tanksDevs.system.player.PlayerPojo;
import com.tanksDevs.system.player.User;
import com.tanksDevs.system.player.UserPojo;
import com.tanksDevs.system.pojo.EntityPojo;

public class NaivePojoParser implements PojoParser {



    @Override
    @SuppressWarnings("unchecked")
    public <T extends Entity, P extends EntityPojo> T parse(P pojo) { // Todo Bullet pojo to entity

        Genre genre = pojo.getGenre();
        T entity = null;

        switch (genre) {

            case EAGLE:
                entity = (T) new Eagle((TankBasePojo) pojo);
                break;
            case FOREST:
                entity = (T) new SimpleForest( (SimpleForestPojo) pojo);
                break;
            case TANK:
                entity = (T) new SimpleTank( (SimpleTankPojo) pojo);
                break;
            case BRICKWALL:
                entity = (T) new BrickWall( (DestroyableWallPojo) pojo);
                break;
            case STEELWALL:
                entity = (T) new SteelWall((SolidWallPojo) pojo);
                break;
            case WATER:
                entity = (T) new SimpleWater((WaterPojo) pojo);
            default:
                break;
        }

        return entity;
    }


    @Override
    @SuppressWarnings("unchecked")
    public <T extends Entity, P extends EntityPojo> P parse(T entity) { // Todo Bullet entity to pojo
        Genre genre = entity.getGenre();
        P pojo = null;

        switch (genre){
            case EAGLE:
                TankBasePojo tankBasePojo = new EaglePojo();
                TankBase tankBase = (Eagle)entity;
                tankBasePojo.setId(entity.getId());
                tankBasePojo.setX(entity.getX());
                tankBasePojo.setY(entity.getY());
                tankBasePojo.setSize(entity.getSize());
                tankBasePojo.setHp(tankBase.getHp());
                tankBasePojo.setGenre(entity.getGenre());
                pojo = (P) tankBasePojo;
                break;
            case FOREST:
                ForestPojo forestPojo = new SimpleForestPojo();
                forestPojo.setId(entity.getId());
                forestPojo.setX(entity.getX());
                forestPojo.setY(entity.getY());
                forestPojo.setSize(entity.getSize());
                forestPojo.setGenre(entity.getGenre());
                pojo = (P) forestPojo;
                break;
            case TANK: // Todo If tank upgarded fill this!
                TankPojo tankPojo = new SimpleTankPojo();
                Tank tank = (SimpleTank) entity;
                tankPojo.setId(entity.getId());
                tankPojo.setX(entity.getX());
                tankPojo.setY(entity.getY());
                tankPojo.setSize(entity.getSize());
                tankPojo.setHp(tank.getHp());
                tankPojo.setGenre(entity.getGenre());
                pojo = (P) tankPojo;
                break;
            case BRICKWALL:
                DestroyableWallPojo destroyableWallPojo = new BrickWallPojo();
                DestroyableWall destroyableWall = (BrickWall) entity;
                destroyableWallPojo.setId(entity.getId());
                destroyableWallPojo.setX(entity.getX());
                destroyableWallPojo.setY(entity.getY());
                destroyableWallPojo.setSize(entity.getSize());
                destroyableWallPojo.setHp(destroyableWall.getHp());
                destroyableWallPojo.setGenre(entity.getGenre());
                pojo = (P) destroyableWallPojo;
                break;
            case STEELWALL:
                SolidWallPojo solidWallPojo = new SteelWallPojo();
                solidWallPojo.setId(entity.getId());
                solidWallPojo.setX(entity.getX());
                solidWallPojo.setY(entity.getY());
                solidWallPojo.setSize(entity.getSize());
                solidWallPojo.setGenre(entity.getGenre());
                pojo = (P) solidWallPojo;
                break;
            case WATER:
                WaterPojo waterPojo = new SimpleWaterPojo();
                waterPojo.setId(entity.getId());
                waterPojo.setX(entity.getX());
                waterPojo.setY(entity.getY());
                waterPojo.setSize(entity.getSize());
                waterPojo.setGenre(entity.getGenre());
                pojo = (P) waterPojo;
            default:
                break;
        }
        return pojo;
    }

    @Override
    public GamePojo parse(Game game) {
        return null;
    }

    @Override
    public Player parse(PlayerPojo playerPojo) {
        return new User(playerPojo);
    }

    @Override
    public PlayerPojo parse(Player player) { // Todo If tank upgarded fill this!
        PlayerPojo playerPojo = new UserPojo();

        TankPojo tankPojo = new SimpleTankPojo();
        SimpleTank tank = (SimpleTank) player.getTank();
        tankPojo.setId(tank.getId());
        tankPojo.setX(tank.getX());
        tankPojo.setY(tank.getY());
        tankPojo.setSize(tank.getSize());
        tankPojo.setHp(tank.getHp());
        tankPojo.setGenre(tank.getGenre());

        playerPojo.setTankPojo(tankPojo);
        playerPojo.setName(player.getName());
        playerPojo.setScore(player.getScore());
        playerPojo.setReady(player.isReady());

        return playerPojo;
    }
}
