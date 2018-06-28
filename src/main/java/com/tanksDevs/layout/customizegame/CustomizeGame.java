package com.tanksDevs.layout.customizegame;

import com.tanksDevs.App;
import com.tanksDevs.layout.Screen;
import com.tanksDevs.layout.utils.Graphics;
import com.tanksDevs.sound.Track;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
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
        HBox menu = new CustomizeMenu(root, app).build();
        ImageView titleImage = new ImageView(TITLE);

        menu.setAlignment(Pos.CENTER);
        StackPane imageContainer = new StackPane(titleImage);
        imageContainer.setAlignment(Pos.TOP_CENTER);

        root.getChildren().add(imageContainer);
        root.getChildren().add(menu);
        root.setBackground(Graphics.getBackground(BACKGROUND));
        return root;
    }
}
