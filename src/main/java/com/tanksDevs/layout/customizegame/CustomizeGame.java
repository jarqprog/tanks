package com.tanksDevs.layout.customizegame;

import com.tanksDevs.App;
import com.tanksDevs.layout.Screen;
import com.tanksDevs.layout.utils.Graphics;
import com.tanksDevs.sound.Track;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

public class CustomizeGame extends Screen {

    private App app;

    public CustomizeGame(App app) {
        this.app = app;
        setRoot(buildRoot());
    }

    @Override
    protected Parent buildRoot() {
        StackPane root = new StackPane();
        root.setBackground(Graphics.getBackground(BACKGROUND));
        return root;
    }
}
