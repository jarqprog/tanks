package com.tanksDevs.layout.button;

import com.tanksDevs.layout.utils.Graphics;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;

public class ButtonMaker {

    private static final String BACKGROUND_UP_PATH = "src/main/resources/gui/button_up.png";
    private static final String BACKGROUND_DOWN_PATH = "src/main/resources/gui/button_down.png";
    private static final String BACKGROUND_HOVER_PATH = "src/main/resources/gui/button_hover.png";

    private static final Color FONT_COLOR_UP = Color.WHITESMOKE;
    private static final Color FONT_COLOR_DOWN = Color.DARKSLATEGRAY;
    private static final Color FONT_COLOR_HOVER = Color.CORAL;

    private static final double FONT_SIZE = 20;
    private static final String FONT_FAMILY = "Verdana";

    private static final Image UP = Graphics.readImage(BACKGROUND_UP_PATH);
    private static final Image DOWN = Graphics.readImage(BACKGROUND_DOWN_PATH);
    private static final Image HOVER = Graphics.readImage(BACKGROUND_HOVER_PATH);

    private StackPane stackPane;
    private ImageView imageView;
    private Label label;

    private Font font;

    public ButtonMaker(String text) {
        label = new Label(text);
        imageView = new ImageView(UP);
        stackPane = new StackPane(imageView, label);
        font = Font.font(FONT_FAMILY, FontPosture.ITALIC, FONT_SIZE);
        createText();
        setConstantListeners();
    }

    private void createText() {
        label.setFont(font);
        label.setTextFill(FONT_COLOR_UP);
    }

    public StackPane getButton() {
        return stackPane;
    }

    private void setConstantListeners() {
        stackPane.setOnMouseEntered(event -> changeStateHover());
        stackPane.setOnMouseExited(event -> changeStateUp());
        stackPane.setOnMouseReleased(event -> changeStateUp());
    }

    private void changeStateUp() {
        imageView.setImage(UP);
        label.setTextFill(FONT_COLOR_UP);
    }

    public void changeStateDown() {
        imageView.setImage(DOWN);
        label.setTextFill(FONT_COLOR_DOWN);
    }

    private void changeStateHover() {
        imageView.setImage(HOVER);
        label.setTextFill(FONT_COLOR_HOVER);
    }

}
