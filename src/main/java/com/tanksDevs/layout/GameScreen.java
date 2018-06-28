package com.tanksDevs.layout;

import com.tanksDevs.App;
import com.tanksDevs.layout.customizegame.CustomizeGame;
import com.tanksDevs.layout.inplay.InPlay;
import com.tanksDevs.layout.mainmenu.MainMenu;
import com.tanksDevs.layout.selectgame.SelectGame;
import javafx.scene.Scene;

public enum GameScreen {
    MAIN_MENU {
        @Override
        public Scene getScene(App app) {
            return new MainMenu(app).buildScene();
        }
    },
    SELECT_GAME {
        @Override
        public Scene getScene(App app) {
            return new SelectGame(app).buildScene();
        }
    },
    CUSTOMIZE_GAME {
        @Override
        public Scene getScene(App app) {
            return new CustomizeGame(app).buildScene();
        }
    },
    IN_PLAY {
        @Override
        public Scene getScene(App app) {
            return new InPlay(app).buildScene();
        }
    };

    public abstract Scene getScene(App app);
}
