package com.tanksDevs.system.game;

import com.tanksDevs.system.entity.tank.Tank;

public class ServerGameLoop {

    private Game game;
    private boolean running;

    public ServerGameLoop(Game game) {
        this.game = game;
        this.running = false;
    }

    public void start() {

        running = true;
        long lastTime = System.nanoTime();
        double tickRate = 60.0;
        double ns = 1000000000 / tickRate;
        double delta = 0;

        while(running) {
            // TODO test thread sleeping to not use CPU 100%
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while(delta >= 1) {
                handleInputs();
                update();
                delta -= 1;
            }
        }
    }

    private void handleInputs() {
        // TODO apply received inputs
//        if (input.contains("W")) tankP1.move(Direction.NORTH);
//        else if (input.contains("S")) tankP1.move(Direction.SOUTH);
//        else if (input.contains("D")) tankP1.move(Direction.EAST);
//        else if (input.contains("A")) tankP1.move(Direction.WEST);
//
//        if (input.contains("UP")) tankP2.move(Direction.NORTH);
//        else if (input.contains("DOWN")) tankP2.move(Direction.SOUTH);
//        else if (input.contains("RIGHT")) tankP2.move(Direction.EAST);
//        else if (input.contains("LEFT")) tankP2.move(Direction.WEST);
    }

    private void update() {
        for (Tank tank : game.getTanks()) {
            tank.move(tank.getDirection());
        }
    }
}
