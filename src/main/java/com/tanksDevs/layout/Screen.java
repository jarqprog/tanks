package com.tanksDevs.layout;

import com.tanksDevs.layout.utils.Graphics;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public abstract class Screen {
    private static final double SCREEN_WIDTH = 800;
    private static final double SCREEN_HEIGHT = 600;

    private static final String BACKGROUND_PATH = "src/main/resources/gui/background.png";
    private static final String TITLE_PATH = "src/main/resources/gui/title.png";

    protected static final Image BACKGROUND = Graphics.readImage(BACKGROUND_PATH);
    protected static final Image TITLE = Graphics.readImage(TITLE_PATH);

    private static final Color SCENE_FILL = Color.BLACK;
    private Parent root;

    protected abstract Parent buildRoot();

    public Scene buildScene() {
        Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
        scene.setFill(SCENE_FILL);
        return scene;
    }

    protected void setRoot(Parent root) {
        this.root = root;
    }
}
