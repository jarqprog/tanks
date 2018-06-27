package com.tanksDevs.network.parser;

import com.tanksDevs.system.entity.Entity;
import com.tanksDevs.system.game.Game;
import com.tanksDevs.system.game.GamePojo;
import com.tanksDevs.system.player.Player;
import com.tanksDevs.system.player.PlayerPojo;
import com.tanksDevs.system.pojo.EntityPojo;

public interface PojoParser {

    <T extends Entity, P extends EntityPojo> T parse(P pojo);
    <T extends Entity, P extends EntityPojo> P parse(T entity);
    Game parse(GamePojo gamePojo);
    GamePojo parse(Game game);
    Player parse(PlayerPojo playerPojo);
    PlayerPojo parse(Player player);

}
