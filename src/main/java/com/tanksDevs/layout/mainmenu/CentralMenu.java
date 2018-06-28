package com.tanksDevs.layout.mainmenu;

import com.tanksDevs.App;
import com.tanksDevs.layout.GameScreen;
import com.tanksDevs.layout.button.ButtonMaker;
import com.tanksDevs.layout.utils.FadeAnimation;
import com.tanksDevs.sound.Music;
import com.tanksDevs.sound.Track;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

class CentralMenu {

    private static final double GAP = 4;

    private StackPane playLocal;
    private StackPane playOnline;
    private StackPane runEditor;
    private StackPane exit;

    private static final String EXIT_TEXT = "Exit";
    private static final String LOCAL_TEXT = "Local";
    private static final String ONLINE_TEXT = "Online";
    private static final String EDIT_MAP_TEXT = "Edit map";

    private static final double INITIAL_ALPHA = 1.0;
    private static final double FINAL_ALPHA = 0.0;
    private static final int ANIMATION_DURATION = 2000;
    private static final double VOLUME = 1.0;

    private Parent parent;
    private App app;

    GridPane build(Parent parent, App app) {
        GridPane menu = new GridPane();
        this.app = app;
        this.parent = parent;
        createButtons();

        menu.setVgap(GAP);
        menu.setHgap(GAP);
        menu.addRow(0, playLocal, playOnline);
        menu.addRow(1, runEditor, exit);
        return menu;
    }

    private void createButtons() {
        ButtonMaker exit = new ButtonMaker(EXIT_TEXT);
        ButtonMaker playLocal = new ButtonMaker(LOCAL_TEXT);
        ButtonMaker playOnline = new ButtonMaker(ONLINE_TEXT);
        ButtonMaker runEditor = new ButtonMaker(EDIT_MAP_TEXT);

        assignButtonsFromButtonMakers(exit, playLocal, playOnline, runEditor);

        createExitListener(exit);
        createPlayLocalListener(playLocal);
        createPlayOnlineListener(playOnline);
        createRunEditorListener(runEditor);
    }

    private void assignButtonsFromButtonMakers(ButtonMaker exit, ButtonMaker playLocal, ButtonMaker playOnline, ButtonMaker runEditor) {
        this.exit = exit.getButton();
        this.playLocal = playLocal.getButton();
        this.playOnline = playOnline.getButton();
        this.runEditor = runEditor.getButton();
    }

    private void createPlayOnlineListener(ButtonMaker playOnline) {
        this.playOnline.setOnMousePressed(event -> {
            if (app.isAllowListeners()) {
                Music.play(Track.EXPLOSION, VOLUME);
                playOnline.changeStateDown();
                animateScreenSwitch(event1 -> {
                    app.setAllowListeners(true);
                    app.setCurrentScreen(GameScreen.SELECT_GAME);
                });
            }
        });
    }

    private void createPlayLocalListener(ButtonMaker playLocal) {
        this.playLocal.setOnMousePressed(event -> {
            if (app.isAllowListeners()) {
                Music.play(Track.EXPLOSION, VOLUME);
                playLocal.changeStateDown();
                animateScreenSwitch(event1 -> {
                    app.setAllowListeners(true);
                    app.setCurrentScreen(GameScreen.CUSTOMIZE_GAME);
                });
            }
        });

    }

    private void createRunEditorListener(ButtonMaker runEditor) {
        this.runEditor.setOnMousePressed(event -> {
            if (app.isAllowListeners()) {
                Music.play(Track.EXPLOSION, VOLUME);
                runEditor.changeStateDown();
                animateScreenSwitch(event1 -> {
                    app.setAllowListeners(true);
                });
            }
        });

    }

    private void createExitListener(ButtonMaker exit) {
        this.exit.setOnMousePressed(event -> {
            if (app.isAllowListeners()) {
                Music.play(Track.EXPLOSION, VOLUME);
                exit.changeStateDown();
                animateScreenSwitch(event1 -> {
                    app.setAllowListeners(true);
                    Platform.exit();
                });
            }
        });

    }

    private void animateScreenSwitch(EventHandler<ActionEvent> event) {
        app.setAllowListeners(false);
        FadeTransition fadeTransition = FadeAnimation.set(parent, INITIAL_ALPHA, FINAL_ALPHA, ANIMATION_DURATION);
        fadeTransition.setOnFinished(event);
        fadeTransition.play();
    }
}
