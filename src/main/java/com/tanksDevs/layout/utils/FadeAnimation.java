package com.tanksDevs.layout.utils;

import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class FadeAnimation {
    public static FadeTransition set(Node nodeToFade, double from, double to, int duration) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(duration), nodeToFade);
        fadeTransition.setFromValue(from);
        fadeTransition.setToValue(to);
        return fadeTransition;
    }
}
