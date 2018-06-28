package com.tanksDevs.system.GUI;

import com.tanksDevs.system.entity.tank.Tank;
import com.tanksDevs.system.game.Game;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;

public class Renderer {

    private static final Color BACKGROUND = Color.BLACK;
    private static final String TITLE = "Tanks";

    private Stage stage;
    private Canvas canvas;
    private GraphicsContext gc;
    private Scene scene;

    private ImageView imageViewTankP1;
    private ImageView imageViewTankP2;

    private Game game;

    public Renderer(Stage stage, Game game) {
        this.stage = stage;
        this.game = game;
        initImageViews();
        initWindow(stage);
    }

    public Scene getScene() {
        return scene;
    }

    private void initImageViews() {
        try {
            imageViewTankP1 = new ImageView(new Image(new FileInputStream("src/main/resources/textures/tank_sand.png")));
            imageViewTankP2 = new ImageView(new Image(new FileInputStream("src/main/resources/textures/tank_green.png")));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        imageViewTankP1.setFitHeight(game.getTileSize());
        imageViewTankP1.setFitWidth(game.getTileSize());
        imageViewTankP2.setFitHeight(game.getTileSize());
        imageViewTankP2.setFitWidth(game.getTileSize());
    }

    private void initWindow(Stage stage) {
        canvas = new Canvas(game.getWidth(), game.getHeight());
        gc = canvas.getGraphicsContext2D();

        Group root = new Group();
        root.getChildren().addAll(canvas, imageViewTankP1, imageViewTankP2);

        scene = new Scene(root);

        stage.setTitle(TITLE);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void render() {
        gc.setFill(BACKGROUND);
        gc.fillRect(0, 0, game.getWidth(), game.getHeight());
        renderEntities();
    }

    private void renderEntities() {
        Iterator<Tank> it = game.getTanks().iterator();
        Tank tankP1 = it.next();
        Tank tankP2 = it.next();
        updateTankImageView(imageViewTankP1, tankP1);
        updateTankImageView(imageViewTankP2, tankP2);
    }

    private void updateTankImageView(ImageView imageView, Tank tank) {

        imageView.setX(tank.getX());
        imageView.setY(tank.getY());

        switch (tank.getDirection()) {
            case NORTH:
                imageView.setRotate(0);
                break;
            case EAST:
                imageView.setRotate(90);
                break;
            case SOUTH:
                imageView.setRotate(180);
                break;
            case WEST:
                imageView.setRotate(270);
                break;
        }
    }
}
