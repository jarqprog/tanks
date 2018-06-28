package com.tanksDevs.network.kryoHelper;

import com.esotericsoftware.kryo.Kryo;
import com.tanksDevs.network.input.UserInput;
import com.tanksDevs.network.states.ServerState;
import com.tanksDevs.network.states.TankState;
import com.tanksDevs.system.entity.Colliding;
import com.tanksDevs.system.entity.Direction;
import com.tanksDevs.system.entity.Genre;
import com.tanksDevs.system.entity.bullet.SimpleBullet;
import com.tanksDevs.system.entity.eagle.EaglePojo;
import com.tanksDevs.system.entity.forest.SimpleForest;
import com.tanksDevs.system.entity.forest.SimpleForestPojo;
import com.tanksDevs.system.entity.hitBox.BasicHitBox;
import com.tanksDevs.system.entity.hitBox.HitBox;
import com.tanksDevs.system.entity.tank.SimpleTank;
import com.tanksDevs.system.entity.tank.SimpleTankPojo;
import com.tanksDevs.system.entity.tank.Tank;
import com.tanksDevs.system.game.SimpleGame;
import com.tanksDevs.system.game.SimpleGamePojo;
import com.tanksDevs.system.player.Player;
import com.tanksDevs.system.player.UserPojo;

import java.util.HashSet;

public class SimpleKryoRegister implements KryoRegister {


    public static KryoRegister create() {
        return new SimpleKryoRegister();
    }

    private SimpleKryoRegister() {}

    @Override
    public void register(Kryo kryo) {

        kryo.register(SimpleForest.class);
        kryo.register(SimpleForestPojo.class);
        kryo.register(SimpleGame.class);
        kryo.register(HashSet.class);
        kryo.register(Tank.class);
        kryo.register(SimpleTank.class);
        kryo.register(Player.class);
        kryo.register(SimpleGamePojo.class);
        kryo.register(SimpleTankPojo.class);
        kryo.register(UserPojo.class);
        kryo.register(Genre.class);
        kryo.register(Direction.class);

        kryo.register(BasicHitBox.class);
        kryo.register(HitBox.class);

        kryo.register(Colliding.class);

        kryo.register(SimpleBullet.class);
        kryo.register(TankState.class);
        kryo.register(UserInput.class);
        kryo.register(ServerState.class);
        kryo.register(EaglePojo.class);

    }
}
