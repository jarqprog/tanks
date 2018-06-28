package com.tanksDevs.layout.selectgame;

import com.tanksDevs.App;
import com.tanksDevs.layout.Screen;
import com.tanksDevs.layout.utils.Graphics;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

public class SelectGame extends Screen {

    private App app;

    public SelectGame(App app) {
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
