package com.tanksDevs.system.game;

import com.tanksDevs.system.board.Board;
import com.tanksDevs.system.entity.Colliding;
import com.tanksDevs.system.entity.Direction;
import com.tanksDevs.system.entity.tank.PlayerTank;
import com.tanksDevs.system.entity.tank.Tank;
import com.tanksDevs.system.player.Player;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SimpleGame extends Application implements Game {

    private static final Color BACKGROUND = Color.BLACK;
    private static final String TITLE = "Tanks";
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int TILE_SIZE = 40;

    private Canvas canvas;
    private GraphicsContext gc;
    private Scene scene;

    private GameLoop gameLoop;

    private Tank tankP1;
    private Tank tankP2;
    private ImageView imageViewTankP1;
    private ImageView imageViewTankP2;
    private ArrayList<String> input;
  
    private Set<Colliding> collidings;
    private Set<Tank> tanks;
    private Set<Player> players;
  
    public SimpleGame(Set<Colliding> collidings, Set<Tank> tanks) {
          this.collidings = collidings;
          this.tanks = tanks;
          this.players = new HashSet<>();
      }

     public SimpleGame(Set<Colliding> collidings, Set<Tank> tanks, Set<Player> players) {
          this(collidings, tanks);
          this.players = players;
      }
  
    @Override
      public Board getBoard() {
          return null;
      }

      @Override
      public Set<Colliding> getCollidings() {
          return collidings;
      }

      @Override
      public Set<Tank> getTanks() {
          return tanks;
      }
  
      @Override
      public boolean markPlayerTank(Tank tank) {

          for(Iterator<Tank> it = tanks.iterator(); it.hasNext(); ) {
              Tank toMark = it.next();
              if (toMark.equals(tank)) {
                  toMark.markAsOccupied();
                  return true;
              }
          }
          return false;
      }

      @Override
      public boolean registerPlayer(Player player) {
          return players.add(player);
      }

      @Override
      public Set<Player> getPlayers() {
          return this.players;
      }

      @Override
      public void setCollidings(Set<Colliding> collidings) {
          this.collidings = collidings;
      }

      @Override
      public void setTanks(Set<Tank> tanks) {
          this.tanks = tanks;
      }

      @Override
      public String toString() {
          return getClass().getSimpleName();
      }

    @Override
    public void start(Stage stage) {

        initImageViews();
        initWindow(stage);
        initInputEvents();
        initEntities();

        gameLoop = new GameLoop();
        gameLoop.start();

    }

    private void initImageViews() {
        try {
            imageViewTankP1 = new ImageView(new Image(new FileInputStream("src/main/resources/textures/tank_sand.png")));
            imageViewTankP2 = new ImageView(new Image(new FileInputStream("src/main/resources/textures/tank_green.png")));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        imageViewTankP1.setFitHeight(TILE_SIZE);
        imageViewTankP1.setFitWidth(TILE_SIZE);
        imageViewTankP2.setFitHeight(TILE_SIZE);
        imageViewTankP2.setFitWidth(TILE_SIZE);
    }

    private void initWindow(Stage stage) {
        canvas = new Canvas(WIDTH, HEIGHT);
        gc = canvas.getGraphicsContext2D();

        Group root = new Group();
        root.getChildren().addAll(canvas, imageViewTankP1, imageViewTankP2);

        scene = new Scene(root);

        stage.setTitle(TITLE);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    private void initInputEvents() {

        input = new ArrayList<String>();

        scene.setOnKeyPressed(
                e -> {
                    String code = e.getCode().toString();
                    if ( !input.contains(code) )
                        input.add( code );
                });

        scene.setOnKeyReleased(
                e -> {
                    String code = e.getCode().toString();
                    input.remove( code );
                });
    }

    private void initEntities() {
        tankP1 = new PlayerTank(50, 50, Direction.NORTH);
        tankP2 = new PlayerTank(400, 400, Direction.NORTH);
    }

    private class GameLoop extends AnimationTimer {


        private long before = System.nanoTime();
        private float delta;

        @Override
        public void handle(long now) {
            delta = (float) ((now - before) / 1E9);

            handleInput(delta);
            updateObjects(delta);

            render(gc);

            before = now;
        }

    }

    private void render(GraphicsContext gc) {
        gc.setFill(BACKGROUND);
        gc.fillRect(0, 0, WIDTH, HEIGHT);
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

    private void handleInput(float delta) {

        if (input.contains("W")) tankP1.move(Direction.NORTH);
        else if (input.contains("S")) tankP1.move(Direction.SOUTH);
        else if (input.contains("D")) tankP1.move(Direction.EAST);
        else if (input.contains("A")) tankP1.move(Direction.WEST);

        if (input.contains("UP")) tankP2.move(Direction.NORTH);
        else if (input.contains("DOWN")) tankP2.move(Direction.SOUTH);
        else if (input.contains("RIGHT")) tankP2.move(Direction.EAST);
        else if (input.contains("LEFT")) tankP2.move(Direction.WEST);

    }

    private void updateObjects(float delta) {

    }
}
