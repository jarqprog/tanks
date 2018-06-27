package com.tanksDevs.network.parser;

import com.tanksDevs.system.entity.Entity;
import com.tanksDevs.system.entity.Genre;
import com.tanksDevs.system.entity.forest.Forest;
import com.tanksDevs.system.entity.forest.SimpleForest;
import com.tanksDevs.system.entity.forest.SimpleForestPojo;
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

            case FOREST:
                entity = (T) new SimpleForest( (SimpleForestPojo) pojo);
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
