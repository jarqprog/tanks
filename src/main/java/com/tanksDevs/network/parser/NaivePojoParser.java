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
import com.tanksDevs.system.game.Game;
import com.tanksDevs.system.game.GamePojo;
import com.tanksDevs.system.player.Player;
import com.tanksDevs.system.player.PlayerPojo;
import com.tanksDevs.system.pojo.EntityPojo;

public class NaivePojoParser implements PojoParser {



    @Override
    @SuppressWarnings("unchecked")
    public <T extends Entity, P extends EntityPojo> T parse(P pojo) {

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
            default:
                break;
        }

        return entity;
    }


    @Override
    public <T extends Entity, P extends EntityPojo> P parse(T entity) {
        return null;
    }

    @Override
    public Game parse(GamePojo gamePojo) {
        return null;
    }

    @Override
    public GamePojo parse(Game game) {
        return null;
    }

    @Override
    public Player parse(PlayerPojo playerPojo) {
        return null;
    }

    @Override
    public PlayerPojo parse(Player player) {
        return null;
    }
}
