package com.tanksDevs;

import com.tanksDevs.layout.GameScreen;
import com.tanksDevs.layout.utils.FadeAnimation;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    private GameScreen currentScreen;
    private Stage stage;
    private boolean allowListeners = true;

    private static final double INITIAL_ALPHA = 0.0;
    private static final double FINAL_ALPHA = 1.0;
    private static final int ANIMATION_DURATION = 2500;

    public void begin(String... args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        stage.setResizable(false);

        setCurrentScreen(GameScreen.MAIN_MENU);
        stage.show();
    }

    public void setCurrentScreen(GameScreen screen) {
        currentScreen = screen;
        Scene scene = screen.getScene(this);
        stage.setScene(scene);
//        animateScene(scene);
    }

    private void animateScene(Scene scene) {
        allowListeners = false;
        FadeTransition fadeTransition = FadeAnimation.set(scene.getRoot(), INITIAL_ALPHA, FINAL_ALPHA, ANIMATION_DURATION);
        fadeTransition.setOnFinished(event -> allowListeners = true);
        fadeTransition.play();
    }

    public GameScreen getCurrentScreen() {
        return currentScreen;
    }

    public boolean isAllowListeners() {
        return allowListeners;
    }

    public void setAllowListeners(boolean allowListeners) {
        this.allowListeners = allowListeners;
    }
}
