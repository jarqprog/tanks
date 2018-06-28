package com.tanksDevs.system.game;

import com.tanksDevs.system.GUI.Renderer;
import javafx.animation.AnimationTimer;

import java.util.ArrayList;

public class ClientGameLoop {

    private Game game;
    private Renderer renderer;
    private ArrayList<String> input;
    private boolean running;

    public ClientGameLoop(Game game, Renderer renderer) {
        this.game = game;
        this.renderer = renderer;
        this.running = false;

        initInputEvents();
    }

    private void initInputEvents() {

        input = new ArrayList<String>();

        renderer.getScene().setOnKeyPressed(
                e -> {
                    String code = e.getCode().toString();
                    if ( !input.contains(code) )
                        input.add( code );
                });

        renderer.getScene().setOnKeyReleased(
                e -> {
                    String code = e.getCode().toString();
                    input.remove( code );
                });
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
                readInputs();
                renderer.render();
                sendInputs();
                delta -= 1;
            }
        }
    }

    private void readInputs() {
        // TODO read inputs
    }

    private void sendInputs() {
        // TODO send inputs to server
    }

}
