package com.tanksDevs;

import com.tanksDevs.layout.GameScreen;
import com.tanksDevs.layout.utils.FadeAnimation;
import com.tanksDevs.sound.Music;
import com.tanksDevs.sound.Track;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;


public class App extends Application {

    private GameScreen currentScreen;
    private Stage stage;
    private boolean allowListeners = true;

    private MediaPlayer mediaPlayer;

    private static final double INITIAL_ALPHA = 0.0;
    private static final double FINAL_ALPHA = 1.0;
    private static final int ANIMATION_DURATION = 2500;

    private static final double VOLUME = 0.5;

    void begin(String... args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        stage.setResizable(false);

        setCurrentScreen(GameScreen.MAIN_MENU);
        stage.show();
        playMusic(Track.MENU);
    }

    public void setCurrentScreen(GameScreen screen) {
        currentScreen = screen;
        Scene scene = screen.getScene(this);
        stage.setScene(scene);
//        animateScene(scene);
    }

    public void playMusic(Track track) {
        if (mediaPlayer.getStatus() != MediaPlayer.Status.DISPOSED) {
            mediaPlayer = Music.playMusic(track, VOLUME, true);
        }
    }

    public void stopMusic() {
        if (mediaPlayer.getStatus() != MediaPlayer.Status.DISPOSED) {
            mediaPlayer.stop();
        }
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
