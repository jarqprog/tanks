package com.tanksDevs.sound;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class Music {

    private static final Media EXPLOSION = new Media(new File("src/main/resources/sound/boom.mp3").toURI().toString());
    private static final Media MENU = new Media(new File("src/main/resources/sound/music_menu.mp3").toURI().toString());
    private static final Media FIGHT = new Media(new File("src/main/resources/sound/music_fight.mp3").toURI().toString());

    public static void play(Track track, double volume) {
        startPlaying(selectMedia(track), volume);
    }

    public static MediaPlayer playMusic(Track track, double volume, boolean repeat) {
        return startMusic(selectMedia(track), volume, repeat);
    }

    private static Media selectMedia(Track track) {
        Media media;
        switch (track) {
            case MENU:
                media = MENU;
                break;
            case EXPLOSION:
                media = EXPLOSION;
                break;
            case FIGHT:
                media = FIGHT;
                break;
            default:
                media = null;
        }
        return media;
    }

    private static void startPlaying(Media media, double volume) {
        new Thread(() -> {
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setVolume(volume);
            mediaPlayer.play();
            mediaPlayer.setOnEndOfMedia(mediaPlayer::dispose);
        }).start();
    }

    private static MediaPlayer startMusic(Media media, double volume, boolean repeat) {
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        new Thread(() -> {
            mediaPlayer.setVolume(volume);
            if (repeat) {
                mediaPlayer.setCycleCount(Integer.MAX_VALUE);
            }
            mediaPlayer.play();
            mediaPlayer.setOnEndOfMedia(mediaPlayer::dispose);
            mediaPlayer.setOnStopped(mediaPlayer::dispose);
        }).start();
        return mediaPlayer;
    }
}
