package com.tanksDevs.sound;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class Music {

    private static final Media EXPLOSION = new Media(new File("src/main/resources/sound/boom.mp3").toURI().toString());
    private static final Media MENU = new Media(new File("src/main/resources/sound/boom.mp3").toURI().toString());

    public static void play(Track track) {
        Media media;
        switch (track) {
            case MENU:
                media = MENU;
                break;
            case EXPLOSION:
                media = EXPLOSION;
                break;
            default:
                media = null;
        }
        startPlaying(media);
    }

    private static void startPlaying(Media media) {
        new Thread(() -> {
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();
            mediaPlayer.setOnEndOfMedia(mediaPlayer::dispose);
        }).start();
    }
}
