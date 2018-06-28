package com.tanksDevs.layout.customizegame;

import com.tanksDevs.App;
import com.tanksDevs.layout.GameScreen;
import com.tanksDevs.layout.button.ButtonMaker;
import com.tanksDevs.layout.utils.FadeAnimation;
import com.tanksDevs.network.NetRoot;
import com.tanksDevs.sound.Music;
import com.tanksDevs.sound.Track;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import java.net.UnknownHostException;

public class CustomizeMenu {

    private static final double GAP = 4;

    private StackPane start;
    private StackPane join;
    private StackPane create;
    private StackPane back;

    private static final String START_TEXT = "Start";
    private static final String JOIN_TEXT = "Join";
    private static final String BACK_TEXT = "Back";
    private static final String CREATE_TEXT = "Create";

    private static final double INITIAL_ALPHA = 1.0;
    private static final double FINAL_ALPHA = 0.0;
    private static final int ANIMATION_DURATION = 2000;
    private static final double VOLUME = 1.0;

    private Parent parent;
    private App app;

    public CustomizeMenu(Parent parent, App app) {
        this.parent = parent;
        this.app = app;
    }

    HBox build() {
        HBox menu = new HBox();
        createButtons();
        menu.setSpacing(GAP);
        menu.getChildren().addAll(start, join, create, back);
        return menu;
    }

    private void createButtons() {
        ButtonMaker start = new ButtonMaker(START_TEXT);
        ButtonMaker join = new ButtonMaker(JOIN_TEXT);
        ButtonMaker create = new ButtonMaker(CREATE_TEXT);
        ButtonMaker back = new ButtonMaker(BACK_TEXT);

        assignButtonsFromButtonMakers(start, join, create, back);

        createStartListener(start);
        createJoinListener(join);
        createCreateListener(create);
        createBackListener(back);
    }

    private void createCreateListener(ButtonMaker create) {
        this.create.setOnMousePressed(event -> {
            if (app.isAllowListeners()) {
                Music.play(Track.EXPLOSION, VOLUME);
                create.changeStateDown();
                animateScreenSwitch(event1 -> {
                    app.setAllowListeners(true);
                    //LOGIC FOR CREATE
                    try {
                        NetRoot.createNetRoot().start(app.getStage());
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                });
            }
        });
    }

    private void createBackListener(ButtonMaker back) {
        this.back.setOnMousePressed(event -> {
            if (app.isAllowListeners()) {
                Music.play(Track.EXPLOSION, VOLUME);
                back.changeStateDown();
                animateScreenSwitch(event1 -> {
                    app.setAllowListeners(true);
                    app.setCurrentScreen(GameScreen.MAIN_MENU);
                });
            }
        });
    }


    private void createJoinListener(ButtonMaker join) {
        this.join.setOnMousePressed(event -> {
            if (app.isAllowListeners()) {
                Music.play(Track.EXPLOSION, VOLUME);
                join.changeStateDown();
                animateScreenSwitch(event1 -> {
                    app.setAllowListeners(true);
                    // LOGIC FOR JOIN
                });
            }
        });
    }

    private void createStartListener(ButtonMaker start) {
        this.start.setOnMousePressed(event -> {
            if (app.isAllowListeners()) {
                Music.play(Track.EXPLOSION, VOLUME);
                start.changeStateDown();
                animateScreenSwitch(event1 -> {
                    app.setAllowListeners(true);
                    // LOGIC FOR START
                });
            }
        });
    }

    private void assignButtonsFromButtonMakers(ButtonMaker start, ButtonMaker join, ButtonMaker create, ButtonMaker back) {
        this.start = start.getButton();
        this.join = join.getButton();
        this.back = back.getButton();
        this.create = create.getButton();
    }

    private void animateScreenSwitch(EventHandler<ActionEvent> event) {
        app.setAllowListeners(false);
        FadeTransition fadeTransition = FadeAnimation.set(parent, INITIAL_ALPHA, FINAL_ALPHA, ANIMATION_DURATION);
        fadeTransition.setOnFinished(event);
        fadeTransition.play();
    }
}
