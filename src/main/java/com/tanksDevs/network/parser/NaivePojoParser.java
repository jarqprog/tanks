package com.tanksDevs.network.parser;

import com.tanksDevs.system.entity.Colliding;
import com.tanksDevs.system.entity.Entity;
import com.tanksDevs.system.entity.Genre;

import com.tanksDevs.system.entity.bullet.Bullet;
import com.tanksDevs.system.entity.bullet.BulletPojo;
import com.tanksDevs.system.entity.bullet.SimpleBullet;
import com.tanksDevs.system.entity.bullet.SimpleBulletPojo;
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
import com.tanksDevs.system.game.SimpleGame;
import com.tanksDevs.system.game.SimpleGamePojo;
import com.tanksDevs.system.player.Player;
import com.tanksDevs.system.player.PlayerPojo;
import com.tanksDevs.system.player.User;
import com.tanksDevs.system.player.UserPojo;
import com.tanksDevs.system.pojo.EntityPojo;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class NaivePojoParser implements PojoParser {


    @Override
    @SuppressWarnings("unchecked")
    public <T extends Entity, P extends EntityPojo> T parse(P pojo) { // Todo Bullet pojo to entity

        Genre genre = pojo.getGenre();
        T entity = null;

        switch (genre) {

            case BULLET:
                entity = (T) new SimpleBullet((BulletPojo) pojo);
                break;
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
            case BULLET:
                BulletPojo bulletPojo = new SimpleBulletPojo();
                Bullet bullet = (SimpleBullet) entity;
                bulletPojo.setId(entity.getId());
                bulletPojo.setX(entity.getX());
                bulletPojo.setY(entity.getY());
                bulletPojo.setSize(entity.getSize());
                bulletPojo.setHp(bullet.getHp());
                bulletPojo.setGenre(entity.getGenre());
                pojo = (P) bulletPojo;
                break;
            case EAGLE:
                TankBasePojo tankBasePojo = new EaglePojo();
                TankBase tankBase = (TankBase) entity;
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
                tankPojo.setIsOccupied(tank.hasPlayer());
                tankPojo.setDirection(tank.getDirection());
                tankPojo.setSpeed(tank.getSpeed());
                EaglePojo eaglePojo = parse (tank.getBase());
                tankPojo.setTankBasePojo(eaglePojo);
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
    @SuppressWarnings("unchecked")
    public GamePojo parse(Game game) {

        GamePojo gamePojo = new SimpleGamePojo();

        Set<TankPojo> tanks = new HashSet<>();
        for (Tank tank : game.getTanks()) {
            tanks.add( parse(tank) );
        }
        gamePojo.setTanks(tanks);

        Set<EntityPojo> collidings = new HashSet<>();
        for (Entity obj : game.getCollidings()) {
            collidings.add( parse( obj) );
        }
        gamePojo.setCollidings(collidings);

        Set<PlayerPojo> players = new HashSet<>();
        for (Player player : game.getPlayers()) {
            players.add( parse(player) );
        }
        gamePojo.setPlayers(players);
        return gamePojo;
    }

    @Override
    public Game parse(GamePojo pojo) {

        Set<Colliding> collidings = new HashSet<>();
        Iterator<EntityPojo> iterator = pojo.getCollidings().iterator();
        while ( iterator.hasNext() ) {
            collidings.add(parse(iterator.next()));
        }

        Set<Tank> tanks = new HashSet<>();
        Iterator<TankPojo> tankIterator = pojo.getTanks().iterator();
        while ( tankIterator.hasNext() ) {
            tanks.add(parse(tankIterator.next()));
        }

        Set<Player> players = new HashSet<>();
        Iterator<PlayerPojo> playerIterator = pojo.getPlayers().iterator();
        while ( playerIterator.hasNext() ) {
            players.add(parse(playerIterator.next()));
        }

        return new SimpleGame(collidings, tanks, players);
    }

    @Override
    public Player parse(PlayerPojo playerPojo) {
        return new User(playerPojo);
    }

    @Override
    public PlayerPojo parse(Player player) { // Todo If tank upgarded fill this!

        PlayerPojo playerPojo = new UserPojo();
        TankPojo tankPojo = new SimpleTankPojo();
        Tank tank = player.getTank();

        if (tank != null) {
            tankPojo.setId(tank.getId());
            tankPojo.setX(tank.getX());
            tankPojo.setY(tank.getY());
            tankPojo.setSize(tank.getSize());
            tankPojo.setHp(tank.getHp());
            tankPojo.setGenre(tank.getGenre());
            tankPojo.setIsOccupied(tank.hasPlayer());
            tankPojo.setTankBasePojo( parse(tank.getBase()) );
            playerPojo.setTankPojo(tankPojo);
        }

        playerPojo.setName(player.getName());
        playerPojo.setScore(player.getScore());
        playerPojo.setReady(player.isReady());

        return playerPojo;
    }
}
